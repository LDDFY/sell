package com.sell.enums;

import lombok.Getter;

/**
 * 商品上下架枚举类
 * Create by: LDDFY
 * Date: 2018/3/25
 */
@Getter
public enum ProductStatusEnums {

    UP(0, "上架"), DOWN(1, "下架");

    private Integer code;
    private String msg;

    ProductStatusEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
