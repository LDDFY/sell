package com.sell.service.impl;

import com.sell.dto.OrderDTO;
import com.sell.enums.ResultEnum;
import com.sell.exception.SellException;
import com.sell.service.BuyerService;
import com.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by: LDDFY
 * Date: 2018/4/9
 */
@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openId, String orderId) throws Exception {
        return checkOrderOwner(openId, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openId, String orderId) throws Exception {
        OrderDTO orderDTO = checkOrderOwner(openId, orderId);
        if (orderDTO == null) {
            log.error("[取消订单]查不到订单 orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOTEXISTS);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openId, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }

        if (!orderDTO.getOrderId().equalsIgnoreCase(openId)) {
            log.error("[查询订单] 订单的openId不一致，openid={},order={}", openId, orderDTO);
            throw new SellException(ResultEnum.ORDER_STATE_ERROR);
        }
        return orderDTO;
    }
}
