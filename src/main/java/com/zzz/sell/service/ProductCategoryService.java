package com.zzz.sell.service;

import com.zzz.sell.dao.ProductCategory;

import java.util.List;

/**
 * 商品类目service
 * @author Jans_zhang
 * 2020/4/28 23:04
 */
public interface ProductCategoryService {
    ProductCategory findOne(Integer categoryId);

    List<ProductCategory>  findAll();

    List<ProductCategory>  findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
