package com.snill.fm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.snill.fm.mapper.OrderItemMapper;
import com.snill.fm.service.OrderItemService;
import com.snill.fm.bean.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public OrderItem getOrderItemById(Integer id) {
        return orderItemMapper.getOrderItemById(id);
    }

    @Override
    public List<OrderItem> getOrderItemList() {
        return orderItemMapper.getOrderItemList();
    }

    @Override
    public int add(OrderItem orderItem) {
        return orderItemMapper.add(orderItem);
    }

    @Override
    public int update(Integer id, OrderItem orderItem) {
        return orderItemMapper.update(id, orderItem);
    }

    @Override
    public int delete(Integer id) {
        return orderItemMapper.delete(id);
    }
}