package com.sell.dao;

import com.sell.entity.ProductCateGroy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * Author: LDDFY
 * Date: 2018/3/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCateGroyRepositoryTest {

    @Autowired
    private ProductCateGroyRepository productCateGroyRepository;

    @Test
    public void findOneTest() {
        Optional<ProductCateGroy> productCateGroy = productCateGroyRepository.findById(2);
        System.out.println(productCateGroy.toString());
    }

    @Test
    public void saveTest() {
        ProductCateGroy productCateGroy = new ProductCateGroy();
        productCateGroy.setCategroyName("测试2");
        productCateGroy.setCategroyType(3);
        productCateGroyRepository.saveAndFlush(productCateGroy);
    }

    @Test
    public void updateTest() {
        Optional<ProductCateGroy> productCateGroy = productCateGroyRepository.findById(2);
        productCateGroy.get().setCategroyName("测试1");
        System.out.println(productCateGroy);
        productCateGroyRepository.saveAndFlush(productCateGroy.get());
    }
}