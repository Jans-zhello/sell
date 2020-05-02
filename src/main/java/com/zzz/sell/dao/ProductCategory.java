package com.zzz.sell.dao;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 商品类目表
 * @author Jans_zhang
 * 2020/4/28 18:03
 */
@Entity
@DynamicUpdate
@Data
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;//类目id

    private String  categoryName;//类目名字

    private Integer categoryType;//类目编号

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
      this.categoryName=categoryName;
      this.categoryType=categoryType;
    }
}
