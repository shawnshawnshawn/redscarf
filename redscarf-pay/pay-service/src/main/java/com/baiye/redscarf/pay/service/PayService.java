package com.baiye.redscarf.pay.service;

import com.baiye.redscarf.common.enums.PayMethodEnum;
import com.baiye.redscarf.pay.wechat.WechatPayUnifiedOrderResponse;
import com.baiye.redscarf.pay.wechat.WechatPrePayRequest;

/**
 * @author baiye
 * @date 2021/3/12 2:55 下午
 **/
public interface PayService {

    PayMethodEnum getPayMethod();

    WechatPayUnifiedOrderResponse unifiedPayOrder(WechatPrePayRequest request);
}
