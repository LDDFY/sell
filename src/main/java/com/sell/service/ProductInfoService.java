package com.sell.service;

import com.sell.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品服务接口
 * Create by: LDDFY
 * Date: 2018/3/25
 */
public interface ProductInfoService {

    /**
     * 根据id查询商品信息
     *
     * @param id 商品id，not null。
     * @return 查询结果
     */
    public ProductInfo findOne(String id);

    /**
     * 查询所有上架商品
     *
     * @return 查询结果
     */
    public List<ProductInfo> findUpAll();

    /**
     * 分页查询所有商品信息
     *
     * @param pageable 分页信息，not null。
     * @return 查询结果
     */
    public Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 保存商品信息
     *
     * @param productInfo 商品信息，not null。
     */
    public ProductInfo save(ProductInfo productInfo) throws Exception;

    /**
     * 根据id删除商品信息
     *
     * @param id 商品id，not null。
     */
    public void deleteById(String id) throws Exception;

    //加库存

    //减库存
}
