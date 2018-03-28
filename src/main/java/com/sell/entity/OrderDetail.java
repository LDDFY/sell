package com.sell.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情表
 * Create by: LDDFY
 * Date: 2018/3/28
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "Order_Detail")
public class OrderDetail implements Serializable {
    @Id
    /** 订单详情id */
    private String detailId;
    /**
     * 订单主表id
     */
    private String orderId;
    /**
     * 订单主表id
     */
    private String productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品价格
     */
    private BigDecimal productPrice;
    /**
     * 商品数量
     */
    private Integer productQuantity;
    /**
     * 商品图片
     */
    private String productIcon;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后更新时间
     */
    private Date updateTime;
}
