package com.baiye.redscarf.pay.wechat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 统一下单返回信息
 * @author 白也
 * @date 2021/1/19 1:44 下午
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@XmlRootElement(name = "xml")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class WechatPayUnifiedOrderResponse extends WechatPayBaseBizResponse {

    /** 公众账号ID. */
    private String appid;

    /** 商户号. */
    @XmlElement(name = "mch_id")
    private String mchId;

    /** 子商户公众账号ID. */
    @XmlElement(name = "sub_appid")
    private String subAppid;

    /** 子商户号. */
    @XmlElement(name = "sub_mch_id")
    private String subMchId;

    /** 设备号. */
    @XmlElement(name = "device_info")
    private String deviceInfo;

    /** 随机字符串. */
    @XmlElement(name = "nonce_str")
    private String nonceStr;

    /** 签名. */
    private String sign;

    /** 交易类型. */
    @XmlElement(name = "trade_type")
    private String tradType;

    /** 预支付交易会话标识. */
    @XmlElement(name = "prepay_id")
    private String prepayId;

    /** 二维码链接. */
    @XmlElement(name = "code_url")
    private String codeUrl;
}


