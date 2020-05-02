package com.zzz.sell.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 订单详情表DAO
 * @author Jans_zhang
 * 2020/4/30 9:15
 */
public interface OrderDetailDAO extends JpaRepository<OrderDetail,String> {
    List<OrderDetail> findByOrderId(String orderId);
}
