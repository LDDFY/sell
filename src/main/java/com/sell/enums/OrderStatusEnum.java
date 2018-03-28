package com.sell.enums;

import lombok.Getter;

/**
 * 订单状态枚举
 * Create by: LDDFY
 * Date: 2018/3/28
 */
@Getter
public enum OrderStatusEnum {
    NEW(0, "新订单"),
    FINISH(1, "完结"),
    CANCEL(2, "已取消");

    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
