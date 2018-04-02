package com.sell.convert;

import com.sell.dto.OrderDTO;
import com.sell.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单数据库对象转换为显示对象
 * Create by: LDDFY
 * Date: 2018/3/31
 */
public class OrderMaster2OrderDTOConvert {
    public static OrderMaster2OrderDTOConvert instance = new OrderMaster2OrderDTOConvert();

    private OrderMaster2OrderDTOConvert() {
    }

    public static OrderMaster2OrderDTOConvert getInstance() {
        return instance;
    }

    public static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convertList(List<OrderMaster> masters) {
        return masters.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
