package com.sell.service.impl;

import com.sell.dto.OrderDTO;
import com.sell.entity.OrderDetail;
import com.sell.enums.OrderStatusEnum;
import com.sell.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by: LDDFY
 * Date: 2018/3/31
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {
    private final String BUYER_OPENID = "123456";
    private final String ORDER_ID = "15224812116407522806";
    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("AA");
        orderDTO.setBuyerAddress("muke");
        orderDTO.setBuyerPhone("12345678901");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("00001");
        o1.setProductQuantity(10);
        orderDetails.add(o1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("00002");
        o2.setProductQuantity(10);
        orderDetails.add(o2);

        orderDTO.setOrderDetails(orderDetails);
        OrderDTO result = orderService.create(orderDTO);
        log.info("创建的商品：result={}", result);
    }

    @Test
    public void findOne() {
        OrderDTO dto = orderService.findOne(ORDER_ID);
        Assert.assertEquals(ORDER_ID, dto.getOrderId());
        log.info("查询商品：result={}", dto);
    }

    @Test
    public void findList() {
        PageRequest request = PageRequest.of(0, 3);
        Page<OrderDTO> page = orderService.findList(BUYER_OPENID, request);
        Assert.assertNotEquals(0, page.getTotalElements());
        log.info("查询商品列表：result={}", page);
    }

    @Test
    public void cancel() throws Exception {
        OrderDTO dto = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.cancel(dto);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
        OrderDTO dto = orderService.findOne("15224810257765970239");
        OrderDTO result = orderService.finish(dto);
        Assert.assertEquals(OrderStatusEnum.FINISH.getCode(), result.getOrderStatus());
    }

    @Test
    public void paid() throws Exception {
        OrderDTO dto = orderService.findOne("123");
        OrderDTO result = orderService.paid(dto);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
    }
}