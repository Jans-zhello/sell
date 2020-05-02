package com.zzz.sell.controller;

import com.zzz.sell.dao.dto.OrderDto;
import com.zzz.sell.enums.ResultEnum;
import com.zzz.sell.exception.SellException;
import com.zzz.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 支付controller
 *
 * @author Jans_zhang
 * 2020/5/1 23:15
 */
@Controller//controller返回的页面,restcontroller返回的是json数据
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId, @RequestParam("returnUrl") String returnUrl) {
       //1、查询订单
        OrderDto orderDto = orderService.findOne(orderId);
        if (orderDto == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //发起支付【由于本人不是金钱主义,故本着奉献和社会主义精神,不开发该功能】
        //返回支付成功页面
       return  new ModelAndView("pay/create");
    }

}
