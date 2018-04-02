package com.sell.enums;

import lombok.Getter;

/**
 * 返回前端异常码
 * Create by: LDDFY
 * Date: 2018/3/31
 */
@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_NOT_ENOUTH(11, "库存不足"),
    ORDER_NOT_EXIST(12, "订单不存在"),
    ORDERDETAIL_NOT_EXIST(12, "订单明细不存在"),
    ORDER_DETAIL_EMPTY(13, "订单明细为空"),
    ORDER_STATE_ERROR(14, "订单状态错误"),
    ORDER_UPDATE_FAIL(15, "订单更新失败");
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
