package com.snill.fm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.snill.fm.bean.JsonResult;
import com.snill.fm.bean.Product;
import com.snill.fm.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "product/save", method = RequestMethod.PUT)
    public ResponseEntity<JsonResult> addProduct(Product product){
        JsonResult r = new JsonResult();
        try {
            int id = productService.save(product);
            r.setResult(id);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @RequestMapping(value = "product/del", method = RequestMethod.DELETE)
    public ResponseEntity<JsonResult> delProduct(Integer id){
        JsonResult r = new JsonResult();
        try {
            productService.delete(id);
            r.setResult(id);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

}
