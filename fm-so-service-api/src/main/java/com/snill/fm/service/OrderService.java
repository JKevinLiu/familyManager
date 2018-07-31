package com.snill.fm.service;

import com.github.pagehelper.PageInfo;
import com.snill.fm.bean.Order;
import com.snill.fm.page.ReqPage;

import java.util.List;

public interface OrderService {
    public Order getOrderById(Integer id);
    public PageInfo<Order> getOrderList(ReqPage page);
    public void save(Order order);
    public void delete(Integer id);
    public List<Order> getOrderByUserIdAndYearMonth(int userId, int yearMonth);
}
