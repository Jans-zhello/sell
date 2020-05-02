package com.zzz.sell.dao.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zzz.sell.dao.OrderDetail;
import com.zzz.sell.util.DateToLongSerilizer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单传输对象
 *
 * @author Jans_zhang
 * 2020/4/30 11:04
 */
@Data
//将值为null的属性不返回给前端
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {
    private String     orderId;//订单iD
    private String     buyerName;//买家名字
    private String     buyerPhone;//买家手机号
    private String     buyerAddress;//买家地址
    private String     buyerOpenid;//买家微信openid
    private BigDecimal orderAmount;//订单总金额
    private Integer    orderStatus;//订单状态 默认为0新下单
    private Integer    payStatus;//支付状态,默认为0未支付
    @JsonSerialize(using = DateToLongSerilizer.class)
    private Date       createTime;//创建时间
    @JsonSerialize(using = DateToLongSerilizer.class)
    private Date       updateTime;//更新时间
    List<OrderDetail> orderDetailList = new ArrayList<>();
}
