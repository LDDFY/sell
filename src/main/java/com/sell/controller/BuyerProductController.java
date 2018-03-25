package com.sell.controller;

import com.sell.vo.ProductInfoVO;
import com.sell.vo.ProductVO;
import com.sell.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * Create by: LDDFY
 * Date: 2018/3/25
 */
@RestController
@RequestMapping("/buyer/product/")
public class BuyerProductController {

    @GetMapping("list")
    public ResultVO get() {
        ResultVO resultVO = new ResultVO();
        ProductVO productVO = new ProductVO();
        ProductInfoVO productInfoVO = new ProductInfoVO();
        productVO.setProductInfoVOList(Arrays.asList(productInfoVO));
        resultVO.setData(Arrays.asList(productVO));
        resultVO.setCode(0);
        resultVO.setMsg("success");
        return resultVO;
    }
}
