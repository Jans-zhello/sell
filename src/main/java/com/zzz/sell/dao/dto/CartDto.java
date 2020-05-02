package com.zzz.sell.dao.dto;

import lombok.Data;

/**
 * @author Jans_zhang
 * 2020/4/30 12:05
 */
@Data
public class CartDto {
    private String  productId;//商品ID
    private Integer productQuantity;//商品数量

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
