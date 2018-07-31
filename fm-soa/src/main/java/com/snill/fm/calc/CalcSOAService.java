package com.snill.fm.calc;

import com.alibaba.dubbo.common.utils.ConcurrentHashSet;
import com.alibaba.dubbo.config.annotation.Reference;
import com.snill.fm.bean.Order;
import com.snill.fm.bean.PayMonth;
import com.snill.fm.bean.User;
import com.snill.fm.service.OrderService;
import com.snill.fm.service.PayMonthService;
import com.snill.fm.service.UserService;
import com.snill.fm.transfer.CalcParams;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class CalcSOAService {
    private static final Logger log = LoggerFactory.getLogger(CalcSOAService.class);

    @Reference
    private UserService userService;

    @Reference
    private OrderService orderService;

    @Reference
    private PayMonthService payMonthService;

    private ExecutorService exec = Executors.newFixedThreadPool(6);
    private Set<Integer> lock = new ConcurrentHashSet<>();

    @KafkaListener(topics = {"calcLastPay"})
    public void calc(ConsumerRecord<String, CalcParams> consumer) { //
        String key = consumer.key();
        CalcParams calcParams = consumer.value();

        int userId = calcParams.getUserId();
        List<Integer> yearMonthList = calcParams.getYearMonthList();

        try {
            //如果正在计算，则直接返回
            if (lock.contains(userId)) {
                return;
            } else {
                lock.add(userId);
            }

            final int size = yearMonthList.size();
            CyclicBarrier cb = new CyclicBarrier(size, new CalcFinishTask());

            for (int i = 0; i < size; i++) {
                int yearMonth = yearMonthList.get(i);
                exec.execute(() -> {
                    try {
                        //首先判断是否已经计算过了
                        PayMonth payMonth = payMonthService.getPayMonthByUserIdAndYearMonth(userId, yearMonth);

                        if(payMonth == null){
                            User user = userService.getUserById(userId);
                            List<Order> orderList = orderService.getOrderByUserIdAndYearMonth(userId, yearMonth);
                            PayMonth curPayMonth = new PayMonth();
                            curPayMonth.setYearMonth(yearMonth);
                            curPayMonth.setUserId(userId);
                            curPayMonth.setUsername(user.getUsername());

                            long totalPrice = 0l;
                            for(Order order : orderList){
                                totalPrice += order.getTotalPrice();
                            }
                            curPayMonth.setTotalPrice(totalPrice);
                            payMonthService.save(curPayMonth);
                        }else{
                            return;
                        }

                        cb.await();
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                    } catch (BrokenBarrierException e) {
                        log.error(e.getMessage());
                    }
                });
            }
        } finally {
            lock.remove(userId);
        }

    }
}