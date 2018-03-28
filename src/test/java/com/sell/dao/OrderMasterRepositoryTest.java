package com.sell.dao;

import com.sell.entity.OrderMaster;
import com.sell.enums.OrderStatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * 订单主表测试类
 * Create by: LDDFY
 * Date: 2018/3/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void save() {
        OrderMaster master = new OrderMaster();
        master.setOrderId("123");
        master.setBuyerName("AA");
        master.setBuyerPhone("123456789");
        master.setBuyerAddress("上海-中国");
        master.setBuyerOpenid("22222222");
        master.setOrderAmount(new BigDecimal(12.3));
        master.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        repository.saveAndFlush(master);
    }

    @Test
    public void findByBuyerOpenId() throws Exception {
        PageRequest request = PageRequest.of(0, 1);
        Page<OrderMaster> page = repository.findByBuyerOpenid("123", request);
        System.out.println(page.getTotalElements());
    }
}