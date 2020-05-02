package com.zzz.sell.service;

import com.zzz.sell.dao.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void findOne() {
        ProductCategory pr = productCategoryService.findOne(3);
        Assert.assertEquals(new Integer(3),pr.getCategoryId());
    }
}