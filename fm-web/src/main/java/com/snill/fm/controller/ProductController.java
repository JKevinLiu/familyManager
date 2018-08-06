package com.snill.fm.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.snill.fm.bean.JsonResult;
import com.snill.fm.bean.Product;
import com.snill.fm.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {

    @Reference
    private ProductService productService;


    @RequestMapping(value = "product/alias", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getAliasProduct(){
        JsonResult r = new JsonResult();
        try {
            List<Product> alias = productService.getAliasProduct();
            r.setResult(alias);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @RequestMapping(value = "product/{id}/AliaAndChildren", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> queryProducts(@PathVariable(value = "id") Integer id){
        JsonResult r = new JsonResult();
        try {
            List<Product> products = productService.getAliaAndChildren(id);
            r.setResult(products);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @RequestMapping(value = "product/{id}/childlist", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getAliasChildren(@PathVariable(value = "id") Integer parentId){
        JsonResult r = new JsonResult();
        try {
            List<Product> children = productService.getProductsByParentId(parentId);
            r.setResult(children);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @RequestMapping(value = "product/{id}/show", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getProductById(@PathVariable(value = "id") Integer id){
        JsonResult r = new JsonResult();
        try {
            Product product = productService.getProductById(id);
            r.setResult(product);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @RequestMapping(value = "product/save", method = RequestMethod.POST)
    public ResponseEntity<JsonResult> addProduct(@RequestBody Product product){
        JsonResult r = new JsonResult();
        try {
            productService.save(product);
            r.setResult("保存成功！");
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @RequestMapping(value = "product/{id}/del", method = RequestMethod.DELETE)
    public ResponseEntity<JsonResult> delProduct(@PathVariable("id") Integer id){
        JsonResult r = new JsonResult();
        try {
            productService.delete(id);
            r.setResult("删除成功");
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }


}