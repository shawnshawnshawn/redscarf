package com.baiye.redscarf.common.enums;

/**
 * @author baiye
 * @date 2021/3/12 10:27 上午
 **/
public enum PayChannelEnum {

    WECHAT_JSAPI("JSAPI", PayMethodEnum.WECHAT_PAY,"微信JSAPI支付"),

    WECHAT_NATIVE("NATIVE", PayMethodEnum.WECHAT_PAY,"微信NATIVE支付"),

    WECHAT_APP("APP", PayMethodEnum.WECHAT_PAY,"微信APP支付"),

    WECHAT_H5("MWEB", PayMethodEnum.WECHAT_PAY,"微信H5支付"),

    WECHAT_MINI("JSAPI", PayMethodEnum.WECHAT_PAY,"微信小程序支付"),
    ;


    private final String method;

    private final PayMethodEnum payMethodEnum;

    private final String comment;


    PayChannelEnum(String method, PayMethodEnum payMethodEnum, String comment) {
        this.comment = comment;
        this.method = method;
        this.payMethodEnum = payMethodEnum;
    }

    public String getComment() {
        return comment;
    }

    public String getMethod() {
        return method;
    }

    public PayMethodEnum getPayMethodEnum() {
        return payMethodEnum;
    }
}
