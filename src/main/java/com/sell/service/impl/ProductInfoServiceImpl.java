package com.sell.service.impl;

import com.sell.dao.ProductInfoRepository;
import com.sell.entity.ProductInfo;
import com.sell.enums.ProductStatusEnums;
import com.sell.service.ProductInfoService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品服务实现类
 * Create by: LDDFY
 * Date: 2018/3/25
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String id) {
        Assert.assertNotNull("id", id);
        return productInfoRepository.findById(id).get();
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnums.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        Assert.assertNotNull("pageable", pageable);
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) throws Exception {
        Assert.assertNotNull("productInfo", productInfo);
        return productInfoRepository.saveAndFlush(productInfo);
    }

    @Override
    public void deleteById(String id) throws Exception {
        Assert.assertNotNull("id", id);
        productInfoRepository.deleteById(id);
    }
}