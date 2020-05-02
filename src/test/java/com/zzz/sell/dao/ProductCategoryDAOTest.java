package com.zzz.sell.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDAOTest {
    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    @Test
    public void findOneTest() {
        Optional<ProductCategory> productCategory = productCategoryDAO.findById(1);
        System.out.println(productCategory.toString());
    }

    @Test
    public void upodateTest() {
        Optional<ProductCategory> productCategory = productCategoryDAO.findById(2);
        if (productCategory.isPresent()) {
            productCategory.get().setCategoryType(3);
            productCategoryDAO.save(productCategory.get());
        }
    }

    @Test
    //@Transactional//执行完毕，自动回滚,即下列方法不会save到数据库。区别于service中@Transactional，service里面是抛出异常才回滚
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory("风味小炒", 11);
        ProductCategory result = productCategoryDAO.save(productCategory);
        Assert.assertNotNull(result);
    }
    @Test
    public void findByCategoryTypeIn(){
        List<Integer> list = Arrays.asList(2,4,8);
        List<ProductCategory> result = productCategoryDAO.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }
}