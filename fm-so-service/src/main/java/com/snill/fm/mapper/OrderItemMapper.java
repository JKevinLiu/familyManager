package com.snill.fm.mapper;

import com.snill.fm.bean.OrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemMapper {

    public OrderItem getOrderItemById(Integer id);
    public List<OrderItem> getOrderItemList();
    public int add(OrderItem orderItem);
    public int update(Integer id, OrderItem orderItem);
    public int delete(Integer id);
}
