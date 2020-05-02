package com.zzz.sell.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDAOTest {
    @Autowired

    private ProductInfoDAO productInfoDAO;

    @Test
    public void saveProductInfo(){
      ProductInfo productInfo = new ProductInfo();
      productInfo.setProductId("100221");
      productInfo.setProductName("鸡蛋炒面");
      productInfo.setProductPrice(new BigDecimal(10));
      productInfo.setProductStock(100);
      productInfo.setProductDescription("好吃不贵,经济实惠");
      productInfo.setProductIcon("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2812334820,2968397435&fm=26&gp=0.jpg");
      productInfo.setProductStatus(0);
      productInfo.setCategoryType(1);
      productInfoDAO.save(productInfo);
    }


    @Test
    public void findByProductStatus() {
        List<ProductInfo> byProductStatus = productInfoDAO.findByProductStatus(0);
        Assert.assertNotEquals(0,byProductStatus.size());
    }
}