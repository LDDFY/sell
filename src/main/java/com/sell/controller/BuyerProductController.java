package com.sell.controller;

import com.sell.entity.ProductCateGroy;
import com.sell.entity.ProductInfo;
import com.sell.service.CateGroyTypeService;
import com.sell.service.ProductInfoService;
import com.sell.util.ResultVoUtil;
import com.sell.vo.ProductInfoVO;
import com.sell.vo.ProductVO;
import com.sell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by: LDDFY
 * Date: 2018/3/25
 */
@RestController
@RequestMapping("/buyer/product/")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private CateGroyTypeService cateGroyTypeService;

    @GetMapping("list")
    public ResultVO get() throws Exception {
        return ResultVoUtil.success(mergeData());
    }

    private List<ProductVO> mergeData() {
        //1、查询所有上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        //2、查询商品中的类目
        List<Integer> types = productInfoList.stream().map(e -> e.getCategroyType()).collect(Collectors.toList());
        List<ProductCateGroy> cateGroys = cateGroyTypeService.findByCategroyTypeIn(types);
        //3、组合数据
        List<ProductVO> data = new ArrayList<>();
        for (ProductCateGroy cateGroy : cateGroys) {
            ProductVO productVO = new ProductVO();
            productVO.setCateGroyType(cateGroy.getCategroyType());
            productVO.setCateGroyName(cateGroy.getCategroyName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<ProductInfoVO>();
            for (ProductInfo info : productInfoList) {
                if (info.getCategroyType().equals(cateGroy.getCategroyType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(info, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }

            productVO.setProductInfoVOList(productInfoVOList);
            data.add(productVO);
        }
        return data;
    }
}
