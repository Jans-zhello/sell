package com.zzz.sell.util;

import com.zzz.sell.dao.OrderMaster;
import com.zzz.sell.dao.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * orderMaster与orderDto对象转换
 *
 * @author Jans_zhang
 * 2020/4/30 14:40
 */
public class OrderMasterToOrderDtoUtil {
    public static OrderDto convert(OrderMaster orderMaster) {
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        return orderDto;
    }
   public static List<OrderDto> convert(List<OrderMaster> orderMasterList){
       return orderMasterList.stream().map(e->
               convert(e)
               ).collect(Collectors.toList());

   }
}
