package com.sell.dao;

import com.sell.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Create by: LDDFY
 * Date: 2018/3/28
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    /**
     * 根据订单id查询订单详情信息
     *
     * @param orderId 订单id
     * @return 查询订单明细结果
     */
    List<OrderDetail> findByOrderId(String orderId);
}
