package com.sell.dao;

import com.sell.entity.ProductCateGroy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
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
    //测试环境中不会实际插入到数据库中
    @Transactional
    public void saveTest() {
        ProductCateGroy productCateGroy = new ProductCateGroy();
        productCateGroy.setCategroyName("测试4");
        productCateGroy.setCategroyType(4);
        productCateGroyRepository.saveAndFlush(productCateGroy);
    }

    @Test
    @Transactional
    public void updateTest() {
        Optional<ProductCateGroy> productCateGroy = productCateGroyRepository.findById(2);
        productCateGroy.get().setCategroyName("测试1");
        System.out.println(productCateGroy);
        productCateGroyRepository.saveAndFlush(productCateGroy.get());
    }

    @Test
    public void findByCateGroyTypeTest() {
        List<Integer> types = Arrays.asList(1, 2);
        List<ProductCateGroy> cateGroys = productCateGroyRepository.findByCategroyTypeIn(types);
        Assert.assertNotEquals(0, cateGroys.size());
    }

    @Test
    public void deleteProductCateGroyTest() {
        productCateGroyRepository.deleteById(1);
    }
}