
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
    public int save(Product product) {
        if(product.getId() == 0){
            return productMapper.add(product);
        }
        return productMapper.update(product);
    }


    @Override
    public int delete(Integer id) {
        return productMapper.delete(id);
    }


    @Override
    public List<Product> getAliasProduct() {
        int parentId = -1;
        return productMapper.getProductsByParentId(parentId);
    }


    @Override
    public List<Product> getProductsByParentId(Integer parentId) {
        return productMapper.getProductsByParentId(parentId);
    }


    @Override
    public List<Product> getAliaAndChildren(Integer id) {
        return productMapper.getAliaAndChildren(id);
    }

}