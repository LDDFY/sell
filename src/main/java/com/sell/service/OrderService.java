package com.sell.service;

import com.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 订单服务层接口
 * Create by: LDDFY
 * Date: 2018/3/31
 */
public interface OrderService {
    /**
     * 创建订单
     */
    OrderDTO create(OrderDTO orderDTO) throws Exception;

    /**
     * 查询单个订单
     */
    OrderDTO findOne(String orderId);

    /**
     * 查询订单列表
     */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /**
     * 取消订单
     */
    OrderDTO cancel(OrderDTO orderDTO) throws Exception;

    /**
     * 完结订单
     */
    OrderDTO finish(OrderDTO orderDTO) throws Exception;

    /**
     * 支付订单
     */
    OrderDTO paid(OrderDTO orderDTO) throws Exception;

}
