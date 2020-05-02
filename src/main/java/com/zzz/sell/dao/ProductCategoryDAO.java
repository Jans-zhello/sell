package com.zzz.sell.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * 商品类目DAO
 */
public interface ProductCategoryDAO extends JpaRepository<ProductCategory,Integer> {
     List<ProductCategory>  findByCategoryTypeIn(List<Integer> categoryTypeList);
}
