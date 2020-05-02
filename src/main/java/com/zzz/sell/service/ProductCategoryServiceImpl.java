package com.zzz.sell.service;

import com.zzz.sell.dao.ProductCategory;
import com.zzz.sell.dao.ProductCategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品类目Service实现
 * @author Jans_zhang
 * 2020/4/28 23:09
 */
@Service
public class ProductCategoryServiceImpl implements  ProductCategoryService{

    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return productCategoryDAO.findById(categoryId).get();
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDAO.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryDAO.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryDAO.save(productCategory);
    }
}
