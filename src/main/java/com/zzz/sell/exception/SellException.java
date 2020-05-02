package com.zzz.sell.exception;

import com.zzz.sell.enums.ResultEnum;

/**
 * @author Jans_zhang
 * 2020/4/30 11:26
 */
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
    public SellException(Integer code,String message){
        super(message);
        this.code = code;
    }
}
