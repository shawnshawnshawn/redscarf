package com.baiye.redscarf.common.util;

import org.apache.commons.lang3.RandomUtils;

import java.util.Date;
import java.util.UUID;

/**
 * 请不要随意修改，如要添加元素，请创建新的类继承该类，在新的类中添加
 *
 * @author 白也
 * @date 2020/11/23 5:41 下午
 */
public class RandomKeyUtils {

    private static final String STR = "1234567890abcdefghigklmnopqrstuvwxyz";

    public static String createRandomKey() {
        return DateUtils.parseStringByFormat(new Date(), "yyyyMMddHHmmssSSS") +
                RandomUtils.nextInt(10000, 99999);
    }

    public static String getKey(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int idx = RandomUtils.nextInt(0, STR.length());
            sb.append(STR.charAt(idx));
        }
        return sb.toString();
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
        String key = getKey(32);
        System.out.println(key);
    }
}
