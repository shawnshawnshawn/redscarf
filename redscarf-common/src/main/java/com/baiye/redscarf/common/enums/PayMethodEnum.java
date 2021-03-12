package com.baiye.redscarf.common.enums;

/**
 * @author baiye
 * @date 2021/3/12 9:55 上午
 **/
public enum PayMethodEnum {

    ALI_PAY(0, "ali_pay", "支付宝支付"),

    WECHAT_PAY(1, "wechat_pay", "微信支付"),

    BALANCE_PAY(2, "balance_pay", "余额支付"),
    ;


    private final Integer type;

    private final String method;

    private final String comment;

    PayMethodEnum(Integer type, String method, String comment) {
        this.type = type;
        this.method = method;
        this.comment = comment;
    }

    public Integer getType() {
        return type;
    }

    public String getMethod() {
        return method;
    }

    public String getComment() {
        return comment;
    }
}
