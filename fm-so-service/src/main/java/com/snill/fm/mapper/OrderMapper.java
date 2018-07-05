package com.snill.fm.mapper;

import com.github.pagehelper.Page;
import com.snill.fm.bean.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

    public Order getOrderById(Integer id);
    public List<Order> getOrderList();
    public int add(Order order);
    public int update(Order order);
    public int delete(Integer id);
    public List<Order> getSimpleOrderList();
}