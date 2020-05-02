package com.zzz.sell.service;

import com.zzz.sell.dao.OrderDetail;
import com.zzz.sell.dao.dto.OrderDto;
import com.zzz.sell.enums.OrderMasterStatusEnums;
import com.zzz.sell.enums.PayStatusEnums;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    private final String BUYEROPENID = "110110";

    @Test
    public void create() {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("Jans_zhang");
        orderDto.setBuyerAddress("俺家");
        orderDto.setBuyerPhone("18337398326");
        orderDto.setBuyerOpenid(BUYEROPENID);

        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("100222");
        orderDetail.setProductQuantity(50);
        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("100221");
        orderDetail2.setProductQuantity(50);
        orderDetailList.add(orderDetail);
        orderDetailList.add(orderDetail2);

        orderDto.setOrderDetailList(orderDetailList);
        OrderDto result = orderService.create(orderDto);
        log.info("创建订单 result={}", result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        OrderDto orderDto = orderService.findOne("1588222972854246015");
        log.info("查询单个订单 result={}", orderDto);
        Assert.assertEquals("1588222972854246015", orderDto.getOrderId());
    }

    @Test
    public void findList() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<OrderDto> list = orderService.findList(BUYEROPENID, pageable);
        Assert.assertNotEquals(0, list.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDto orderDto = orderService.findOne("1588222972854246015");
        OrderDto result = orderService.cancel(orderDto);
        Assert.assertEquals(OrderMasterStatusEnums.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDto orderDto = orderService.findOne("1588222972854246015");
        OrderDto result = orderService.finish(orderDto);
        Assert.assertEquals(OrderMasterStatusEnums.FINISHED.getCode(), result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDto orderDto = orderService.findOne("1588225454421217471");
        OrderDto result = orderService.paid(orderDto);
        Assert.assertEquals(PayStatusEnums.SUCCESS.getCode(), result.getPayStatus());
    }
}