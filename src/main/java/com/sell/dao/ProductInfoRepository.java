package com.sell.dao;

import com.sell.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品主表数据访问
 * Create by: LDDFY
 * Date: 2018/3/25
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    /**
     * 根据商品状态查询商品信息。
     *
     * @param status 商品状态，0：上架，1：下架
     * @return 查询结果
     */
    public List<ProductInfo> findByProductStatus(Integer status);
}
