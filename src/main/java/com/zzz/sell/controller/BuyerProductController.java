package com.zzz.sell.controller;

import com.zzz.sell.dao.ProductCategory;
import com.zzz.sell.dao.ProductInfo;
import com.zzz.sell.service.ProductCategoryService;
import com.zzz.sell.service.ProductInfoService;
import com.zzz.sell.util.ResultViewUtil;
import com.zzz.sell.view.ProductCategoryView;
import com.zzz.sell.view.ProductInfoView;
import com.zzz.sell.view.ResultView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品controller
 *  【方法照着API开发】
 * @author Jans_zhang
 * 2020/4/29 17:56
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultView list() {
        //1、查询所有的上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        //2、查询类目(一次性查询)
        //传统方法
/*        List<Integer> categoryTypeList = new ArrayList<Integer>();
        for (ProductInfo productInfo:upAll)
        {
             categoryTypeList.add(productInfo.getCategoryType());
        }*/
        //jdk1.8方法
        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);
        //3、数据拼装
        List<ProductCategoryView> productCategoryViewList = new LinkedList<ProductCategoryView>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductCategoryView productCategoryView = new ProductCategoryView();
            productCategoryView.setCategoryName(productCategory.getCategoryName());
            productCategoryView.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoView> productInfoViewList = new LinkedList<ProductInfoView>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType() == productCategory.getCategoryType()) {
                    ProductInfoView productInfoView = new ProductInfoView();
                    productInfoView.setProductId(productInfo.getProductId());
                    productInfoView.setProductName(productInfo.getProductName());
                    productInfoView.setProductDescription(productInfo.getProductDescription());
                    productInfoView.setProductIcon(productInfo.getProductIcon());
                    productInfoView.setProductPrice(productInfo.getProductPrice());
                    productInfoViewList.add(productInfoView);
                }
            }
            productCategoryView.setProductInfoViewList(productInfoViewList);
            productCategoryViewList.add(productCategoryView);
        }

        return ResultViewUtil.success(productCategoryViewList);
    }
}
