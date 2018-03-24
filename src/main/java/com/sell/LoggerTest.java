package com.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    /*第一种方式每个测试类都新建一个logger对象*/
    private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test1() {
        logger.info("info..");
        logger.debug("debug..");
        logger.error("error..");
    }

    @Test
    public void test2() {
        //需要引入lombok 并在测试类上添加@Slf4j注解
        log.info("info{},{}", 1, 2);
        log.debug("debug..");
        log.error("error..");
    }

}
