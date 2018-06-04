package com.snill.fm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.snill.fm.mapper.OrderMapper;
import com.snill.fm.service.OrderService;
import com.snill.fm.bean.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    //@Reference
    //private UserService userService;

    @Override
    public Order getOrderById(Integer id) {
        return orderMapper.getOrderById(id);
    }

    @Override
    public List<Order> getOrderList() {
        return orderMapper.getOrderList();
    }

    @Override
    public int add(Order order) {
        return orderMapper.add(order);
    }

    @Override
    public int update(Integer id, Order order) {
        return orderMapper.update(id, order);
    }

    @Override
    public int delete(Integer id) {

        return orderMapper.delete(id);
    }
}