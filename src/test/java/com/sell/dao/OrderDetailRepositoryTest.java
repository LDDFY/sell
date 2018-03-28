package com.sell.dao;

import com.sell.entity.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单详情表测试类
 * Create by: LDDFY
 * Date: 2018/3/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void save() {
        OrderDetail detail = new OrderDetail();
        detail.setDetailId("222");
        detail.setOrderId("456");
        detail.setProductId("789");
        detail.setProductName("101212");
        detail.setProductPrice(new BigDecimal(21.8));
        detail.setProductQuantity(12);
        detail.setProductIcon("www");
        repository.saveAndFlush(detail);
    }

    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> details = repository.findByOrderId("456");
        System.out.println(details.size());
    }
}