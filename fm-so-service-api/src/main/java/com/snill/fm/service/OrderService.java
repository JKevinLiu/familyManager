package com.snill.fm.service;

import com.snill.fm.so.Order;

import java.util.List;

public interface OrderService {
    public Order getOrderById(Integer id);
    public List<Order> getOrderList();
    public int add(Order order);
    public int update(Integer id, Order order);
    public int delete(Integer id);
}
