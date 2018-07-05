package com.snill.fm.controller;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alibaba.dubbo.config.annotation.Reference;
import com.snill.fm.bean.JsonResult;
import com.snill.fm.bean.Order;
import com.snill.fm.page.ReqPage;
import com.snill.fm.service.OrderService;


@RestController
public class OrderController {


    @Reference
    private OrderService orderService;


    @RequestMapping(value = "order/{id}/show", method = RequestMethod.GET)
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


    @RequestMapping(value = "order/list", method = RequestMethod.POST)
    public ResponseEntity<JsonResult> getOrderList(@RequestBody ReqPage reqPage){
        JsonResult r = new JsonResult();
        try {
            PageInfo<Order> pageInfo = orderService.getOrderList(reqPage);
            r.setResult(pageInfo);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @RequestMapping(value = "order/save", method = RequestMethod.POST)
    public ResponseEntity<JsonResult> saveOrder(@RequestBody Order order){
        JsonResult r = new JsonResult();
        try {
            orderService.save(order);
            r.setResult("操作成功！");
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @RequestMapping(value = "order/{id}/del", method = RequestMethod.DELETE)
    public ResponseEntity<JsonResult> delProduct(@PathVariable("id") Integer id){
        JsonResult r = new JsonResult();
        try {
            orderService.delete(id);
            r.setResult("删除成功！");
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }
}
