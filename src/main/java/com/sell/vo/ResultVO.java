package com.sell.vo;

import lombok.Data;

/**
 * http请求返回对象
 * Create by: LDDFY
 * Date: 2018/3/25
 */
@Data
public class ResultVO<T> {
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 返回信息
     */
    private T data;
}
