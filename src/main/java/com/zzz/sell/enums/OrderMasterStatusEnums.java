package com.zzz.sell.enums;

import lombok.Getter;

/**
 * 订单状态枚举
 */
@Getter
public enum OrderMasterStatusEnums {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "取消");
    private Integer code;
    private String  message;
    OrderMasterStatusEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
