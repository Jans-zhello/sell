package com.zzz.sell.dao;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 订单详情表
 * @author Jans_zhang
 * 2020/4/30 9:07
 */
@Entity
@Data
public class OrderDetail {
    @Id
    private String detailId;//订单详情ID
    private String orderId;//订单ID
    private String productId;//商品Id
    private String productName;//商品名称
    private BigDecimal productPrice;//商品单价
    private Integer productQuantity;//商品数量
    private String productIcon;//商品小图
}
