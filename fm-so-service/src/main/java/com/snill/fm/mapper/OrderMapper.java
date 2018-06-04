package com.snill.fm.mapper;

import com.snill.fm.bean.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

    public Order getOrderById(Integer id);
    public List<Order> getOrderList();
    public int add(Order order);
    public int update(Integer id, Order order);
    public int delete(Integer id);
}