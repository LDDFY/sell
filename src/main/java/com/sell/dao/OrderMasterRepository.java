package com.sell.dao;

import com.sell.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Create by: LDDFY
 * Date: 2018/3/28
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    /**
     * 分页查询指定用户的订单
     *
     * @param buyerOpenId   用户id
     * @param pageable 分页信息
     * @return 返回查询分页结果
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenId, Pageable pageable);
}
