package com.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品包含类目
 * Create by: LDDFY
 * Date: 2018/3/25
 */
@Data
public class ProductVO {

    @JsonProperty("name")
    private String cateGroyName;

    @JsonProperty("type")
    private Integer cateGroyType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
