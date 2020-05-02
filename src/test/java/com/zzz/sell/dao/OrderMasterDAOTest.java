package com.zzz.sell.dao;

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

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDAOTest {

    @Autowired
    private OrderMasterDAO orderMasterDAO;

    private final String OPENID = "110110";

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("真的爱你");
        orderMaster.setBuyerPhone("18337398326");
        orderMaster.setBuyerAddress("大兴");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(20.3));
        OrderMaster save = orderMasterDAO.save(orderMaster);
        Assert.assertNotNull(save);
    }


    @Test
    public void findByBuyerOpenid() {
        Pageable pageable = PageRequest.of(0,1);
        Page<OrderMaster> result = orderMasterDAO.findByBuyerOpenid(OPENID,pageable);
        System.out.println(result.getTotalElements());
    }
}