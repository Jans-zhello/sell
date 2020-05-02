package com.zzz.sell.service;

import com.zzz.sell.dao.*;
import com.zzz.sell.dao.dto.CartDto;
import com.zzz.sell.dao.dto.OrderDto;
import com.zzz.sell.enums.OrderMasterStatusEnums;
import com.zzz.sell.enums.PayStatusEnums;
import com.zzz.sell.enums.ResultEnum;
import com.zzz.sell.exception.SellException;
import com.zzz.sell.util.OrderMasterToOrderDtoUtil;
import com.zzz.sell.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 订单service实现
 *
 * @author Jans_zhang
 * 2020/4/30 11:17
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailDAO orderDetailDAO;

    @Autowired
    private OrderMasterDAO orderMasterDAO;

    @Override
    @Transactional
    //创建订单
    public OrderDto create(OrderDto orderDto) {
        //定义订单总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //订单ID
        String orderId = RandomUtil.genUniqueKey();
        //1、查询商品(数量，价格)
        for (OrderDetail orderDetail : orderDto.getOrderDetailList()) {
            System.out.println("given Id:" + orderDetail.getProductId());
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2、计算总价
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
            //3、写入订单详情数据库
            orderDetail.setDetailId(RandomUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailDAO.save(orderDetail);
        }
        //4、写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setOrderId(orderId);
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderMasterStatusEnums.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnums.WAIT.getCode());
        orderMasterDAO.save(orderMaster);
        //4、扣库存
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().map(e -> new CartDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.decreaseStock(cartDtoList);
        return orderDto;
    }

    //查询订单
    @Override
    public OrderDto findOne(String orderId) {
        Optional<OrderMaster> ordermaster = orderMasterDAO.findById(orderId);
        if (!ordermaster.isPresent()) throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        List<OrderDetail> orderDetails = orderDetailDAO.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetails)) throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(ordermaster.get(), orderDto);
        orderDto.setOrderDetailList(orderDetails);
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterDAO.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDto> list = OrderMasterToOrderDtoUtil.convert(orderMasterPage.getContent());
        Page<OrderDto> orderDtoPage = new PageImpl<>(list, pageable, orderMasterPage.getTotalElements());
        return orderDtoPage;
    }

    //取消订单
    @Override
    public OrderDto cancel(OrderDto orderDto) {
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderMasterStatusEnums.NEW.getCode())) {
            log.error("订单状态不正确 orderId={},orderStatus={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDto.setOrderStatus(OrderMasterStatusEnums.CANCEL.getCode());
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster updateResult = orderMasterDAO.save(orderMaster);
        if (updateResult == null) {
            log.error("更新失败");
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        //增加库存
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("订单中无订单详情");
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().map(e -> new CartDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.increaseStock(cartDtoList);
        //如果已经支付,需要退款
        if (orderDto.getPayStatus().equals(PayStatusEnums.SUCCESS.getCode())) { //TODO


        }

        return orderDto;
    }

    //完结订单
    @Override
    public OrderDto finish(OrderDto orderDto) {
        //判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderMasterStatusEnums.NEW.getCode())) {
            log.error("完结订单 订单状态不正确 orderId={},orderStatus={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDto.setOrderStatus(OrderMasterStatusEnums.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster result = orderMasterDAO.save(orderMaster);
        if (result == null) {
            log.error("完结订单 更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        return orderDto;
    }

    //支付订单
    @Override
    public OrderDto paid(OrderDto orderDto) {
        //判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderMasterStatusEnums.NEW.getCode())) {
            log.error("支付订单 订单状态不正确 orderId={},orderStatus={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if (!orderDto.getPayStatus().equals(PayStatusEnums.WAIT.getCode())) {
            log.error("支付订单 支付状态不正确,orderDto={}", orderDto);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        orderDto.setPayStatus(PayStatusEnums.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster result = orderMasterDAO.save(orderMaster);
        if (result == null) {
            log.error("支付订单 更新失败,orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        return orderDto;
    }
}
