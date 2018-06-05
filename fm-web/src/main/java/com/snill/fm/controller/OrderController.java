package com.snill.fm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.snill.fm.bean.Order;
import com.snill.fm.jsonbean.JsonResult;
import com.snill.fm.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Reference
    private OrderService orderService;

    @RequestMapping(value = "order/{id}", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getOrderById (@PathVariable(value = "id") Integer id){
        JsonResult r = new JsonResult();
        try {
            Order order = orderService.getOrderById(id);
            r.setResult(order);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @RequestMapping(value = "order/list", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getOrderList (){
        JsonResult r = new JsonResult();
        try {
            List<Order> orderList = orderService.getOrderList();
            r.setResult(orderList);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }
}
