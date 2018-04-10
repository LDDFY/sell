package com.sell.service;

import com.sell.dto.OrderDTO;

/**
 * 查询订单
 * Create by: LDDFY
 * Date: 2018/4/9
 */
public interface BuyerService {
    /**
     * 查询一个订单
     *
     * @param openId  买家openId,not null。
     * @param orderId 订单id，not null。
     * @return 查询结果
     */
    public OrderDTO findOrderOne(String openId, String orderId) throws Exception;

    /**
     * 取消一个订单
     *
     * @param openId  买家openId,not null。
     * @param orderId 订单id，not null。
     * @return 操作结果
     */
    public OrderDTO cancelOrder(String openId, String orderId) throws Exception;
}
