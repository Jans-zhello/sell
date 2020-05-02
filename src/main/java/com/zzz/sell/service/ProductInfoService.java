package com.zzz.sell.service;

import com.zzz.sell.dao.ProductInfo;
import com.zzz.sell.dao.dto.CartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品service
 * @author Jans_zhang
 * 2020/4/29 16:56
 */
public interface ProductInfoService {
    ProductInfo findOne(String productId);

    List<ProductInfo> findUpAll();
    Page<ProductInfo> findAll(Pageable pageable);//分页查询

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDto> cartDtoList);
    //减库存
   void decreaseStock(List<CartDto> cartDtoList);
}
