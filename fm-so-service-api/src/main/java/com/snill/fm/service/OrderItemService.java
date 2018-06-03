package com.snill.fm.service;

import com.snill.fm.so.OrderItem;

import java.util.List;

public interface OrderItemService {
    public OrderItem getOrderItemById(Integer id);
    public List<OrderItem> getOrderItemList();
    public int add(OrderItem orderItem);
    public int update(Integer id, OrderItem orderItem);
    public int delete(Integer id);
}
