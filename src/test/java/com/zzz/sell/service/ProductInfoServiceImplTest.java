package com.zzz.sell.service;

import com.zzz.sell.dao.ProductInfo;
import com.zzz.sell.enums.ProductInfoStatusEnums;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoService productInfoService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productInfoService.findOne("100221");
        Assert.assertEquals("100221",productInfo.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> upAll = productInfoService.findUpAll();
        Assert.assertNotEquals(0,upAll.size());
    }

    @Test
    public void findAll() {
        Pageable pageable =PageRequest.of(0,2);
        Page<ProductInfo> all = productInfoService.findAll(pageable);
        System.out.println(all.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("100224");
        productInfo.setProductName("烤肉饭");
        productInfo.setProductPrice(new BigDecimal(20));
        productInfo.setProductStock(100);
        productInfo.setProductIcon("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588325865328&di=035727070b5ab93e3148600114249791&imgtype=0&src=http%3A%2F%2Fhao.qudao.com%2Fupload%2Farticle%2F20180314%2F81956128981521019430.jpg");
        productInfo.setProductDescription("经济实惠,好吃不贵");
        productInfo.setProductStatus(ProductInfoStatusEnums.UP.getCode());
        productInfo.setCategoryType(1);
        productInfoService.save(productInfo);
    }
}