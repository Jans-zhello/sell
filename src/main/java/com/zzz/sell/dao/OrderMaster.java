package com.zzz.sell.dao;

import com.zzz.sell.enums.OrderMasterStatusEnums;
import com.zzz.sell.enums.PayStatusEnums;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表
 *
 * @author Jans_zhang
 * 2020/4/30 8:47
 */
@Entity
@DynamicUpdate
@Data
public class OrderMaster {
    @Id
    private String     orderId;//订单iD
    private String     buyerName;//买家名字
    private String     buyerPhone;//买家手机号
    private String     buyerAddress;//买家地址
    private String     buyerOpenid;//买家微信openid
    private BigDecimal orderAmount;//订单总金额
    private Integer    orderStatus = OrderMasterStatusEnums.NEW.getCode();//订单状态 默认为0新下单
    private Integer    payStatus   = PayStatusEnums.WAIT.getCode();//支付状态,默认为0未支付
    private Date       createTime;//创建时间
    private Date       updateTime;//更新时间
}
