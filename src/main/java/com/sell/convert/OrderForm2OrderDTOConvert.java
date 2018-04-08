package com.sell.convert;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sell.dto.OrderDTO;
import com.sell.entity.OrderDetail;
import com.sell.enums.ResultEnum;
import com.sell.exception.SellException;
import com.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单参数请求转换为DTO对象
 * Create by: LDDFY
 * Date: 2018/4/2
 */
@Slf4j
public class OrderForm2OrderDTOConvert {
    public static OrderForm2OrderDTOConvert instance = new OrderForm2OrderDTOConvert();

    private OrderForm2OrderDTOConvert() {

    }

    public static OrderForm2OrderDTOConvert getInstance() {
        return instance;
    }

    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerOpenid(orderForm.getOpenId());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        List<OrderDetail> list = new ArrayList<OrderDetail>();
        try {
            list = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("对象转换错误");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetails(list);
        return orderDTO;
    }
}
