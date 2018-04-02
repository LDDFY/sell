package com.sell.util;

import java.util.Random;

/**
 * 主键生成类
 * Create by: LDDFY
 * Date: 2018/3/31
 */
public class KeyUtil {
    /**
     * 时间+随机数
     *
     * @return
     */
    public static synchronized String generateUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(9000000) + 1000000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
