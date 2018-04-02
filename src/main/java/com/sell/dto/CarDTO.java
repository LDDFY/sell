package com.sell.dto;

import lombok.Data;

/**
 * 购物车对象
 * Create by: LDDFY
 * Date: 2018/3/31
 */
@Data
public class CarDTO {
    private String productId;
    private Integer productQuantity;

    public CarDTO() {
    }

    public CarDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
