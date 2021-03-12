package com.baiye.redscarf.pay.wechat;

import lombok.Data;

import java.math.BigDecimal;

/**
 *
 * @author 白也
 * @date 2021/1/19 2:13 下午
 */
@Data
public class WechatPrePayRequest {

    private BigDecimal amount;

    private String outTradeNo;

    private String ip;

    private String productId;
}
