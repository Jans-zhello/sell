package com.zzz.sell.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

/**
 * http请求返回前端的商品类目对象pojo
 *
 * @author Jans_zhang
 * 2020/4/29 21:36
 */
@Data
public class ProductCategoryView {
    @JsonProperty("name")
    private String                categoryName;
    @JsonProperty("type")
    private Integer               categoryType;
    @JsonProperty("foods")
    private List<ProductInfoView> productInfoViewList;
}
