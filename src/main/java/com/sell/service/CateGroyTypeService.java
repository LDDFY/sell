package com.sell.service;

import com.sell.entity.ProductCateGroy;

import java.util.List;

/**
 * 商品类目服务接口
 * Create by: LDDFY
 * Date: 2018/3/25
 */
public interface CateGroyTypeService {

    /**
     * 根据id查找商品类目。
     *
     * @param id 商品类目id，not null。
     * @return 查询结果
     */
    public ProductCateGroy findOne(Integer id);

    /**
     * 查询所有商品类目信息。
     *
     * @return 查询结果
     */
    public List<ProductCateGroy> findAll();

    /**
     * 根据类目编号查询。
     *
     * @param types 类目编号集合，not null。
     * @return
     */
    public List<ProductCateGroy> findByCategroyTypeIn(List<Integer> types);

    /**
     * 保存商品类目信息。
     *
     * @param entity 要保存的商品类目信息，not null。
     * @return
     */
    public ProductCateGroy saveCateGroy(ProductCateGroy entity) throws Exception;

    /**
     * 根据id删除商品类目信息。
     *
     * @param id 要删除的商品类目id，not null。
     * @throws Exception
     */
    public void deleteCateGroyById(Integer id) throws Exception;
}
