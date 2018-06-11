package com.snill.fm.mapper;


import com.snill.fm.bean.Product;


import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface ProductMapper {

    public Product getProductById(Integer id);
    public List<Product> getProductList();
    public int add(Product product);
    public int update(Product product);
    public int delete(Integer id);
    public List<Product> getProductsByParentId(int parentId);
    public List<Product> getAliaAndChildren(Integer id);

}