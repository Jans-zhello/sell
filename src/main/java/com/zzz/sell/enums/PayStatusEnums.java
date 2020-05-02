package com.zzz.sell.enums;

import lombok.Getter;

/**
 * 支付状态枚举
 * @author Jans_zhang
 * 2020/4/30 9:00
 */
@Getter
public enum  PayStatusEnums {
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功");

    PayStatusEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    private Integer code;
    private String  message;

}
