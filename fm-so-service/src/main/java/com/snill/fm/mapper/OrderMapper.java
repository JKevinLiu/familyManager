package com.snill.fm.mapper;

import com.snill.fm.bean.Order;
import org.apache.ibatis.annotations.Param;
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
    public List<Order> getOrderByUserIdAndYearMonth(@Param("userId") int userId, @Param("yearMonth") int yearMonth);
}