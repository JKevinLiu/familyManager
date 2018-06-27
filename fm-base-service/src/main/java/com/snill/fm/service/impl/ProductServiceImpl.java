package com.snill.fm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.snill.fm.mapper.ProductMapper;
import com.snill.fm.service.ProductService;
import com.snill.fm.bean.Product;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service(interfaceClass = ProductService.class)
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
    public void save(Product product) {
        if(product == null){
            throw new IllegalArgumentException("产品信息为空！");
        }
        if(product.getCode() == null || "".equalsIgnoreCase(product.getCode().trim())){
            throw new IllegalArgumentException("产品编码不能为空！");
        }


        if(product.getName() == null || "".equalsIgnoreCase(product.getName().trim())){
            throw new IllegalArgumentException("产品名称不能为空！");
        }


        //默认为大类
        if(product.getParentId() == 0){
            product.setParentId(-1);
        }


        if(product.getId() == 0){
            //检查唯一性
            Product hasProduct = productMapper.getProductByCode(product.getCode());
            if(hasProduct != null){
                throw new IllegalArgumentException("产品编码已存在！");
            }


            productMapper.add(product);
        }

        productMapper.update(product);
    }

    @Override
    public void delete(Integer id) {
        productMapper.delete(id);
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