package com.zzz.sell.view;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * http请求接收前端form表单的订单对象Pojo
 *
 * @author Jans_zhang
 * 2020/4/30 21:05
 */
@Data
public class OrderView {

    @NotEmpty(message = "姓名必填")
    private String name;//买家姓名
    @NotEmpty(message = "手机号必填")
    private String phone;//买家手机号
    @NotEmpty(message = "地址必填")
    private String address;//买家地址
    @NotEmpty(message = "openid必填")
    private String openid;//买家openid
    @NotEmpty(message = "购物车不能为空")
    private String items;//买家购物车
}
