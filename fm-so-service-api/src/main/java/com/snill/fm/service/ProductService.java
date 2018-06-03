package com.snill.fm.service;

import com.snill.fm.so.Product;

import java.util.List;

public interface ProductService {
    public Product getProductById(Integer id);
    public List<Product> getProductList();
    public int add(Product product);
    public int update(Integer id, Product product);
    public int delete(Integer id);
}
