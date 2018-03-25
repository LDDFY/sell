package com.sell.service.impl;

import com.sell.entity.ProductInfo;
import com.sell.enums.ProductStatusEnums;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Create by: LDDFY
 * Date: 2018/3/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productInfoService.findOne("00001");
        Assert.assertNotNull(productInfo);
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        Assert.assertNotEquals(0, productInfoList.size());
    }

    @Test
    public void findAll() {
        PageRequest request = PageRequest.of(0, 1);
        Page<ProductInfo> page = productInfoService.findAll(request);
        System.out.println(page.getTotalElements());
    }

    @Test
    public void save() throws Exception {
        ProductInfo info = new ProductInfo();
        info.setProductId("00002");
        info.setProductName("测试商品2");
        info.setProductPrice(new BigDecimal(12.3));
        info.setProductStock(100);
        info.setProductDescription("测试商品");
        info.setProductIcon("www");
        info.setProductStatus(ProductStatusEnums.DOWN.getCode());
        info.setCategroyType(1);
        ProductInfo result = productInfoService.save(info);
        System.out.println(result.toString());
    }

    @Test
    public void deleteById() throws Exception{
        productInfoService.deleteById("00002");
    }
}