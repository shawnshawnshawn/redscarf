package com.baiye.redscarf.common.limiter;

import com.baiye.redscarf.common.enums.EnumMsg;
import lombok.AllArgsConstructor;

/**
 * 请不要随意修改，如要添加元素，请创建新的类继承该类，在新的类中添加
 *
 * @author 白也
 * @date 2020/11/17 2:23 下午
 */
@AllArgsConstructor
public enum RequestLimitEnum implements EnumMsg<String> {
    IP("ip", "IP限制"),

    METHOD("method", "方法名限制"),

    TOKEN("token", "token限制"),
    ;

    private final String code;

    private final String msg;

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String getCode() {
        return code;
    }
}
