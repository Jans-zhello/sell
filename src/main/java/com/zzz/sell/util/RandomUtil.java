package com.zzz.sell.util;

import java.util.Random;

/**
 * 随机数工具类
 * @author Jans_zhang
 * 2020/4/30 11:49
 */
public class RandomUtil {
    /**
     * 生成唯一的主键
     * 格式:时间+随机数
     * @return
     */
    public static  synchronized  String genUniqueKey(){
        Random random = new Random();
        Integer num = random.nextInt(900000)+100000;
        return System.currentTimeMillis() + String.valueOf(num);
    }
}
