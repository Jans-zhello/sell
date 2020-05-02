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
public class OrderDetailDAOTest {

    @Autowired
    private OrderDetailDAO orderDetailDAO;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123456789");
        orderDetail.setOrderId("1111111");
        orderDetail.setProductIcon("XXXXXXXX");
        orderDetail.setProductId("100220");
        orderDetail.setProductName("test");
        orderDetail.setProductPrice(new BigDecimal(15));
        orderDetail.setProductQuantity(2);
        OrderDetail save = orderDetailDAO.save(orderDetail);
        Assert.assertNotNull(save);

    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = orderDetailDAO.findByOrderId("1111111");
        Assert.assertNotEquals(0,orderDetailList.size());
    }
}