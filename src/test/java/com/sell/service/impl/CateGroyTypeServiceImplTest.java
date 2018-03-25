package com.sell.service.impl;

import com.sell.entity.ProductCateGroy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * 商品类目服务测试类
 * Create by: LDDFY
 * Date: 2018/3/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CateGroyTypeServiceImplTest {

    @Autowired
    private CateGroyTypeServiceImpl cateGroyTypeService;

    @Test
    public void findOne() {
        ProductCateGroy cateGroy = cateGroyTypeService.findOne(10);
        //Assert.assertNotEquals(new Integer(10),cateGroy.getCategroyId());
    }

    @Test
    public void findAll() {
        List<ProductCateGroy> cateGroys = cateGroyTypeService.findAll();
        Assert.assertNotEquals(0, cateGroys.size());
    }

    @Test
    public void findByCategroyTypeIn() {
        List<Integer> types = Arrays.asList(2);
        List<ProductCateGroy> cateGroys = cateGroyTypeService.findByCategroyTypeIn(types);
        Assert.assertNotEquals(0, cateGroys.size());
    }

    @Test
    public void saveCateGroy() throws Exception {
        ProductCateGroy cateGroy = new ProductCateGroy();
        cateGroy.setCategroyName("類目1");
        cateGroy.setCategroyType(5);
        cateGroyTypeService.saveCateGroy(cateGroy);
    }

    @Test
    public void deleteCateGroyById() throws Exception {
        cateGroyTypeService.deleteCateGroyById(6);
    }
}