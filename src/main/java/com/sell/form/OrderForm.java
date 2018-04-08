package com.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 订单表单验证
 * Create by: LDDFY
 * Date: 2018/4/2
 */
@Data
public class OrderForm {

    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名必填")
    private String name;

    /**
     * 买家手机号
     */
    @NotEmpty(message = "手机号必填")
    private String phone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "地址必填")
    private String address;

    /**
     * 买家微信openId
     */
    @NotEmpty(message = "openId必填")
    private String openId;

    /**
     * 买家购物车
     */
    @NotEmpty(message = "购物车必填")
    private String items;
}
