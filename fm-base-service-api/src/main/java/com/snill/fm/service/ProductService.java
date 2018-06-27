package com.snill.fm.service;


import com.snill.fm.bean.Product;


import java.util.List;


public interface ProductService {
    public Product getProductById(Integer id);
    public List<Product> getProductList();
    public void save(Product product);
    public void delete(Integer id);
    public List<Product> getAliasProduct();
    public List<Product> getProductsByParentId(Integer parentId);
    public List<Product> getAliaAndChildren(Integer id);
}