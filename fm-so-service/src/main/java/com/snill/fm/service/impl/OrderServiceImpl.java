package com.snill.fm.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snill.fm.bean.OrderItem;
import com.snill.fm.mapper.OrderItemMapper;
import com.snill.fm.mapper.OrderMapper;
import com.snill.fm.page.ReqPage;
import com.snill.fm.service.OrderService;
import com.snill.fm.bean.Order;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service(interfaceClass=OrderService.class)
public class OrderServiceImpl implements OrderService {
    private static Logger log = Logger.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    @Cacheable(value="orderCache", keyGenerator = "keyGenerator")
    public Order getOrderById(Integer id) {
        return orderMapper.getOrderById(id);
    }

    @Override
    @Cacheable(value="orderListCache", keyGenerator = "keyGenerator")
    public PageInfo<Order> getOrderList(ReqPage page) {
        PageHelper.startPage(page.getCurPage(), page.getPageSize());
        List<Order> orderList = orderMapper.getSimpleOrderList();
        PageInfo<Order> pageInfo = new PageInfo<>(orderList);
        return pageInfo;
    }

    @Override
    public void save(Order order) {
        if(order == null){
            throw new IllegalArgumentException("订单为空！");
        }

        List<OrderItem> orderItemList = order.getOrderItemList();

        int totalPrice = 0;
        for(OrderItem item : orderItemList){
            totalPrice += item.getPrice();
        }
        order.setTotalPrice(totalPrice);

        Calendar calendar = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(calendar.getTime());
        int yearMonth = Integer.parseInt(date.substring(0,4));

        if(order.getId() != 0){
            List<OrderItem> updateOrderItemList = new ArrayList<>();
            List<OrderItem> saveOrderItemList = new ArrayList<>();

            orderItemList.forEach(item -> {
                if(item.getId() == 0){
                    item.setCreateDate(date);
                    item.setOrderId(order.getId());
                    saveOrderItemList.add(item);
                }else{
                    updateOrderItemList.add(item);
                }
            });
            order.setYearMonth(yearMonth);
            orderMapper.update(order);

            if(updateOrderItemList.size() > 0){
                orderItemMapper.batchUpdate(updateOrderItemList);
            }
            if(saveOrderItemList.size() > 0){
                orderItemMapper.batchAdd(saveOrderItemList);
            }

        }else{
            order.setCreateDate(date);
            orderMapper.add(order);

            orderItemList.forEach(item -> {
                item.setCreateDate(date);
                item.setOrderId(order.getId());
            });

            orderItemMapper.batchAdd(orderItemList);
        }
    }

    @Override
    public void delete(Integer id) {
        orderItemMapper.deleteOrderItemByOrderId(id);
        orderMapper.delete(id);
    }

    @Override
    public List<Order> getOrderByUserIdAndYearMonth(int userId, int yearMonth) {
        return orderMapper.getOrderByUserIdAndYearMonth(userId, yearMonth);
    }
}