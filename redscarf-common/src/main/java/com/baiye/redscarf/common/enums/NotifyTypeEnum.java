package com.baiye.redscarf.common.enums;

/**
 * @author baiye
 * @since 2021/8/19 2:58 下午
 **/
public enum NotifyTypeEnum implements EnumMsg<Integer>{

    CORP_WECHAT(0, "企业微信"),

    ALI_DING(1, "钉钉"),

    ;

    private Integer code;

    private String msg;

    NotifyTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
