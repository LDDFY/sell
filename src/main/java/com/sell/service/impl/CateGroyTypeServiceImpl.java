package com.sell.service.impl;

import com.sell.dao.ProductCateGroyRepository;
import com.sell.entity.ProductCateGroy;
import com.sell.service.CateGroyTypeService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品类目服务实现类
 * Create by: LDDFY
 * Date: 2018/3/25
 */
@Service
public class CateGroyTypeServiceImpl implements CateGroyTypeService {

    @Autowired
    private ProductCateGroyRepository repository;

    @Override
    public ProductCateGroy findOne(Integer id) {
        Assert.assertNotNull("id", id);
        return repository.findById(id).get();
    }

    @Override
    public List<ProductCateGroy> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCateGroy> findByCategroyTypeIn(List<Integer> types) {
        Assert.assertNotNull("types", types);
        return repository.findByCategroyTypeIn(types);
    }

    @Override
    public ProductCateGroy saveCateGroy(ProductCateGroy entity) throws Exception {
        Assert.assertNotNull("entity", entity);
        return repository.saveAndFlush(entity);
    }

    @Override
    public void deleteCateGroyById(Integer id) throws Exception {
        Assert.assertNotNull("id", id);
        repository.deleteById(id);
    }
}
