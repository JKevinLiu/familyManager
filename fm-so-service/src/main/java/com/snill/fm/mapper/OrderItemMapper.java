package com.snill.fm.mapper;


import com.snill.fm.bean.OrderItem;


import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface OrderItemMapper {


    public OrderItem getOrderItemById(Integer id);
    public List<OrderItem> getOrderItemListByOrderId(int orderId);
    public int add(OrderItem orderItem);
    public int update(OrderItem orderItem);
    public int delete(Integer id);
    public void deleteOrderItemByOrderId(Integer orderId);

    public void batchAdd(List<OrderItem> orderItemList);
    public void batchUpdate(List<OrderItem> orderItemList);
}