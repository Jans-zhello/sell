package com.zzz.sell.enums;

import lombok.Getter;

/**
 *
 * 商品状态枚举
 * @author Jans_zhang
 * 2020/4/29 17:12
 */
@Getter
public enum ProductInfoStatusEnums {
    UP(0,"上架"),
    DOWN(1,"下架")
    ;
    private Integer code;
    private String message;
    ProductInfoStatusEnums(Integer code,String message) {
        this.code = code;
        this.message = message;
    }
}
