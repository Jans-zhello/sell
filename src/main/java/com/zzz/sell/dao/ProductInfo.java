package com.zzz.sell.dao;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 商品表
 * @author Jans_zhang
 * 2020/4/29 16:05
 */
@Entity
@Data
public class ProductInfo {
       @Id
      private String productId;//id

      private String productName;//名字

      private BigDecimal productPrice;//单价

      private Integer productStock;//库存

      private String productDescription;//描述

      private String productIcon;//小图

      private Integer productStatus;//状态 0正常1下架

      private Integer categoryType;//类目编号

}
