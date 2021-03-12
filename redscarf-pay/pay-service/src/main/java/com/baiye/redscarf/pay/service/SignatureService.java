package com.baiye.redscarf.pay.service;

import com.baiye.redscarf.common.enums.PayChannelEnum;

import java.util.Map;

/**
 * 支付签名服务
 * @author baiye
 * @date 2021/3/12 10:48 上午
 **/
public interface SignatureService {

    PayChannelEnum getChannel();

    /**
     * 对参数进行签名
     */
    String sign(Map<String, String> parameters);

    /**
     * 验证参数签名
     */
    Boolean verify(Map<String, String> parameters);
}
