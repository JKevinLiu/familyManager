package com.snill.fm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.snill.fm.mapper.ProductMapper;
import com.snill.fm.service.ProductService;
import com.snill.fm.bean.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product getProductById(Integer id) {
        return productMapper.getProductById(id);
    }

    @Override
    public List<Product> getProductList() {
        return productMapper.getProductList();
    }

    @Override
    public int add(Product product) {
        return productMapper.add(product);
    }

    @Override
    public int update(Integer id, Product product) {
        return productMapper.update(id, product);
    }

    @Override
    public int delete(Integer id) {
        return productMapper.delete(id);
    }
}