package com.baiye.redscarf.pay.wechat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 白也
 * @date 2021/1/20 11:30 上午
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = "xml")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class WechatPayAsyncNotifyResponse extends WechatPayBaseBizResponse{

    @XmlElement(name = "openid")
    private String openid = "";

    @XmlElement(name = "trade_type")
    private String tradeType;

    @XmlElement(name = "bank_type")
    private String bankType;

    @XmlElement(name = "total_fee")
    private Integer totalFee;

    @XmlElement(name = "transaction_id")
    private String transactionId;

    @XmlElement(name = "out_trade_no")
    private String outTradeNo;

    @XmlElement(name = "attach")
    private String attach;

    @XmlElement(name = "time_end")
    private String timeEnd;

    /**
     * 是否成功.
     */
    public boolean isSuccess() {
        return super.isBizSuccess();
    }
}
