package com.sell.dao;

import com.sell.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品信息服务层测试类
 * Create by: LDDFY
 * Date: 2018/3/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void saveTest() {
        ProductInfo info = new ProductInfo();
        info.setProductId("00001");
        info.setProductName("测试商品");
        info.setProductPrice(new BigDecimal(12.3));
        info.setProductStock(100);
        info.setProductDescription("测试商品");
        info.setProductIcon("www");
        info.setProductStatus(0);
        info.setCategroyType(1);
        ProductInfo result = productInfoRepository.save(info);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> result = productInfoRepository.findByProductStatus(0);
        Assert.assertNotEquals(0, result.size());
    }
}