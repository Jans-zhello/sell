package com.zzz.sell.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品DAO
 * @author Jans_zhang
 * 2020/4/29 16:15
 */
public interface ProductInfoDAO extends JpaRepository<ProductInfo,String> {
    List<ProductInfo> findByProductStatus(Integer productStatus);

}
