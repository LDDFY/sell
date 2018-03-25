package com.sell.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品信息表
 * Author: LDDFY
 * Date: 2018/3/24
 */
@Data
@Entity
@Table(name = "product_info")
public class ProductInfo implements Serializable {

    @Id
    /** 商品主键*/
    private String productId;
    /** 商品名称*/
    private String productName;
    /** 单价*/
    private BigDecimal productPrice;
    /** 库存*/
    private Integer productStock;
    /** 描述*/
    private String productDescription;
    /** 图片链接*/
    private String productIcon;
    /** 商品上下架0：上架，1：下架*/
    private Integer productStatus;
    /** 商品类目*/
    private Integer categroyType;
    /** 创建时间*/
    private Date createTime;
    /** 修改时间*/
    private Date updateTime;

    @Override
    public String toString() {
        return "ProductInfo{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productStock=" + productStock +
                ", productDescription='" + productDescription + '\'' +
                ", productIcon='" + productIcon + '\'' +
                ", productStatus=" + productStatus +
                ", categroyType=" + categroyType +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
