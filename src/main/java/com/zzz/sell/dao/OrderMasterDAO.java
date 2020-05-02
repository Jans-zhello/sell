package com.zzz.sell.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单表DAO
 * @author Jans_zhang
 * 2020/4/30 9:11
 */
public interface OrderMasterDAO extends JpaRepository<OrderMaster,String > {
   Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
