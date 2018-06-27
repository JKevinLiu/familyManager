package com.snill.fm.service;

import com.snill.fm.bean.OrderItem;

import java.util.List;

public interface OrderItemService {
    public OrderItem getOrderItemById(Integer id);
    public List<OrderItem> getOrderItemListByOrderId(Integer id);
    public void save(OrderItem orderItem);
    public void delete(Integer id);
}
