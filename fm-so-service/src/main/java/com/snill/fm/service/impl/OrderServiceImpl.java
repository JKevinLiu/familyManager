package com.snill.fm.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.snill.fm.bean.OrderItem;
import com.snill.fm.mapper.OrderItemMapper;
import com.snill.fm.mapper.OrderMapper;
import com.snill.fm.page.ReqPage;
import com.snill.fm.service.OrderService;
import com.snill.fm.bean.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service(interfaceClass=OrderService.class)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;


    @Override
    public Order getOrderById(Integer id) {
        return orderMapper.getOrderById(id);
    }

    @Override
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

        if(order.getId() != 0){
            orderMapper.update(order);
            orderItemMapper.batchUpdate(orderItemList);
        }else{

            Calendar calendar = Calendar.getInstance();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = df.format(calendar.getTime());
            order.setCreateDate(date);

            int orderId = orderMapper.add(order);

            orderItemList.forEach(item -> {
                item.setCreateDate(date);
                item.setOrderId(order.getId());
            });

            /*if(order != null){
                throw new IllegalArgumentException("事务测试！");
            }*/

            orderItemMapper.batchAdd(orderItemList);
        }
    }

    @Override
    public void delete(Integer id) {
        orderItemMapper.deleteOrderItemByOrderId(id);
        orderMapper.delete(id);
    }
}