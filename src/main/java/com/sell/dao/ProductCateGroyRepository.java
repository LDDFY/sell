package com.sell.dao;

import com.sell.entity.ProductCateGroy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品类目表数据访问
 * Author: LDDFY
 * Date: 2018/3/24
 */
public interface ProductCateGroyRepository extends JpaRepository<ProductCateGroy, Integer> {

    /**
     * 查询指定编号的类目
     *
     * @param categroyTypes 类目编号集合，not null。
     * @return 查询结果
     */
    public List<ProductCateGroy> findByCategroyTypeIn(List<Integer> categroyTypes);
}
