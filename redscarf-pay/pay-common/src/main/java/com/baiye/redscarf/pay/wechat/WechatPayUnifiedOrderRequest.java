package com.baiye.redscarf.pay.wechat;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author 白也
 * @date 2021/1/19 10:24 上午
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@XmlRootElement(name = "xml")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class WechatPayUnifiedOrderRequest extends WechatBaseRequest{

    /** 公众账号ID. */
    private String appid;

    /** 商户号. */
    @XmlElement(name = "mch_id")
    private String mchId;

    /** 设备号. */
    @XmlElement(name = "device_info")
    private String deviceInfo;

    /** 随机字符串. */
    @XmlElement(name = "nonce_str")
    private String nonceStr;

    /** 签名. */
    private String sign;

    /** 商品描述. */
    private String body;

    /** 商品详情. */
    private String detail;

    /** 附加数据. */
    private String attach;

    /** 商户订单号. */
    @XmlElement(name = "out_trade_no")
    private String outTradeNo;

    /** 货币类型. */
    @XmlElement(name = "fee_type")
    private String feeType;

    /** 总金额. */
    @XmlElement(name = "total_fee")
    private int totalFee;

    /** 终端IP. */
    @XmlElement(name = "spbill_create_ip")
    private String spbillCreateIp;

    /** 交易起始时间. */
    @XmlElement(name = "time_start")
    private String timeStart;

    /** 交易结束时间. */
    @XmlElement(name = "time_expire")
    private String timeExpire;

    /** 商品标记. */
    @XmlElement(name = "goods_tag")
    private String goodsTag;

    /** 通知地址. */
    @XmlElement(name = "notify_url")
    private String notifyUrl;

    /** 交易类型. */
    @XmlElement(name = "trade_type")
    private String tradeType;

    /** 商品ID. */
    @XmlElement(name = "product_id")
    private String productId;

    /** 指定支付方式. */
    @XmlElement(name = "limit_pay")
    private String limitPay;

    /** 用户标识. */
    private String openid;
}
