package com.snill.fm.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.snill.fm.mapper.OrderItemMapper;
import com.snill.fm.service.OrderItemService;
import com.snill.fm.bean.OrderItem;


import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;


@Service(interfaceClass=OrderItemService.class)
public class OrderItemServiceImpl implements OrderItemService {


    @Autowired
    private OrderItemMapper orderItemMapper;


    @Override
    public OrderItem getOrderItemById(Integer id) {
        return orderItemMapper.getOrderItemById(id);
    }


    @Override
    public List<OrderItem> getOrderItemListByOrderId(Integer orderId) {
        return orderItemMapper.getOrderItemListByOrderId(orderId);
    }


    @Override
    public void save(OrderItem orderItem) {
        if(orderItem == null){
            throw new IllegalArgumentException("订单明细为空！");
        }

        if(orderItem.getId() == 0){
            orderItemMapper.add(orderItem);
        }else{
            orderItemMapper.update(orderItem);
        }
    }


    @Override
    public void delete(Integer id) {
        orderItemMapper.delete(id);
    }
}