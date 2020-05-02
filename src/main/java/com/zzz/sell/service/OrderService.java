package com.zzz.sell.service;

import com.zzz.sell.dao.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 订单service
 * @author Jans_zhang
 * 2020/4/30 10:03
 */
public interface OrderService {
    //创建订单
    OrderDto create(OrderDto orderDto);
    //查询单个订单
    OrderDto findOne(String orderId);
    //查询订单列表
    Page<OrderDto> findList(String buyerOpenid, Pageable pageable);
    //取消订单
    OrderDto cancel(OrderDto orderDto);
    //完结订单
    OrderDto finish(OrderDto orderDto);
    //支付订单
    OrderDto paid(OrderDto orderDto);
}
