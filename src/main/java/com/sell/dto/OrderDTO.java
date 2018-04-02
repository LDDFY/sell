package com.sell.dto;

import com.sell.entity.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单传输对象
 * Create by: LDDFY
 * Date: 2018/3/31
 */
@Data
public class OrderDTO {
    /**
     * 订单主表id
     */
    private String orderId;
    /**
     * 买家姓名
     */
    private String buyerName;

    /**
     * 买家电话
     */
    private String buyerPhone;
    /**
     * 买家地址
     */
    private String buyerAddress;
    /**
     * 买家微信号
     */
    private String buyerOpenid;
    /**
     * 买家金额
     */
    private BigDecimal orderAmount;
    /**
     * 订单状态默认0
     */
    private Integer orderStatus;
    /**
     * 支付状态
     */
    private Integer payStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后更新时间
     */
    private Date updateTime;
    /**
     * 订单详情
     */
    private List<OrderDetail> orderDetails;
}
