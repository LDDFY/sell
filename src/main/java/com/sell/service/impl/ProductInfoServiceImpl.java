package com.sell.service.impl;

import com.sell.dao.ProductInfoRepository;
import com.sell.dto.CarDTO;
import com.sell.entity.ProductInfo;
import com.sell.enums.ProductStatusEnums;
import com.sell.enums.ResultEnum;
import com.sell.exception.SellException;
import com.sell.service.ProductInfoService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return productInfoRepository.getOne(id);
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

    @Override
    @Transactional
    public void increaseStock(List<CarDTO> carDTOList) throws Exception {
        for (CarDTO car : carDTOList) {
            ProductInfo info = productInfoRepository.getOne(car.getProductId());
            if (info == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer stock = info.getProductStock() + car.getProductQuantity();
            if (stock < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_NOT_ENOUTH);
            }
            info.setProductStock(stock);
            productInfoRepository.save(info);
        }

    }

    @Override
    @Transactional
    public void decreaseStock(List<CarDTO> carDTOList) throws Exception {
        for (CarDTO car : carDTOList) {
            ProductInfo info = productInfoRepository.getOne(car.getProductId());
            if (info == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer stock = info.getProductStock() - car.getProductQuantity();
            info.setProductStock(stock);
            productInfoRepository.save(info);
        }
    }
}
