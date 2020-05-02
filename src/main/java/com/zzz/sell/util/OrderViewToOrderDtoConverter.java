package com.zzz.sell.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zzz.sell.dao.OrderDetail;
import com.zzz.sell.dao.dto.OrderDto;
import com.zzz.sell.enums.ResultEnum;
import com.zzz.sell.exception.SellException;
import com.zzz.sell.view.OrderView;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * OrderView与OrderDto对象转换
 *
 * @author Jans_zhang
 * 2020/4/30 21:21
 */
@Slf4j
public class OrderViewToOrderDtoConverter {
    public static OrderDto convert(OrderView orderView) {
        Gson gson = new Gson();
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName(orderView.getName());
        orderDto.setBuyerPhone(orderView.getPhone());
        orderDto.setBuyerAddress(orderView.getAddress());
        orderDto.setBuyerOpenid(orderView.getOpenid());
        List<OrderDetail> list = new ArrayList<>();
        /**
         * string/json转换成list
         */
        try {
            list = gson.fromJson(orderView.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (Exception e) {
            log.error("对象转换错误,string={}", orderView.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDto.setOrderDetailList(list);
        return orderDto;
    }
}
