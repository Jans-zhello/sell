package com.zzz.sell.controller;

import com.zzz.sell.dao.dto.OrderDto;
import com.zzz.sell.enums.ResultEnum;
import com.zzz.sell.exception.SellException;
import com.zzz.sell.service.OrderService;
import com.zzz.sell.util.OrderViewToOrderDtoConverter;
import com.zzz.sell.util.ResultViewUtil;
import com.zzz.sell.view.OrderView;
import com.zzz.sell.view.ResultView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 三大Utils：BeanUtils CollectionUtils StringUtils
 */

/**
 * 买家订单controller
 * 【方法照着APT开发】
 *
 * @author Jans_zhang
 * 2020/4/30 20:57
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;

    //创建订单
    @PostMapping("/create")
    public ResultView<Map<String, String>> create(@Valid OrderView orderView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("创建订单 参数不正确,orderView={}", orderView);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto = OrderViewToOrderDtoConverter.convert(orderView);
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("创建订单 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDto orderDto1 = orderService.create(orderDto);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", orderDto1.getOrderId());
        return ResultViewUtil.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResultView<List<OrderDto>> list(@RequestParam("openid") String openid, @RequestParam(value = "Page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("订单列表 openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderDto> orderPage = orderService.findList(openid, pageable);
        return ResultViewUtil.success(orderPage.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    public ResultView<OrderDto> detail(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId) {
        //TODO不安全的做法,需要改进
        OrderDto orderDto = orderService.findOne(orderId);
        if (orderDto == null) return null;
        if (!orderDto.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("订单详情 不是本人");
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);

        }
        return ResultViewUtil.success(orderDto);

    }
    //取消订单
   @PostMapping("/cancel")
    public ResultView cancel(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId){
       //TODO不安全的做法,需要改进
        OrderDto orderDto = orderService.findOne(orderId);
       if (orderDto == null) {
        log.error("取消订单 查不到该订单");
        throw new SellException(ResultEnum.ORDER_NOT_EXIST);
       }
       if (!orderDto.getBuyerOpenid().equalsIgnoreCase(openid)){
           log.error("取消订单 不是本人");
           throw new SellException(ResultEnum.ORDER_OWNER_ERROR);

       }
       orderService.cancel(orderDto);
       return  ResultViewUtil.success();
   }
}
