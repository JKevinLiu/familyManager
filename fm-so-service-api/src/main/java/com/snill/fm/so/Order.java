package com.snill.fm.so;

import java.sql.Date;
import java.util.List;
import com.snill.fm.bean.base.User;

public class Order {
    private int id;

    private User user;
    private List<OrderItem> orderList;

    private Date createDate;
    private int deleted;
}
