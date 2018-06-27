package com.snill.fm.service;

import com.snill.fm.bean.Order;

import java.util.List;

public interface OrderService {
    public Order getOrderById(Integer id);
    public List<Order> getOrderList();
    public void save(Order order);
    public void delete(Integer id);
}
