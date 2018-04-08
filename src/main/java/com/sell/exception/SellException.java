package com.sell.exception;

import com.sell.enums.ResultEnum;

/**
 * Create by: LDDFY
 * Date: 2018/3/31
 */
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum, String msg) {
        super(msg);
        this.code = resultEnum.getCode();
    }

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
