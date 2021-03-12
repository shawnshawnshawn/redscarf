package com.baiye.redscarf.pay.service.impl;

import com.alibaba.fastjson.JSON;
import com.baiye.redscarf.common.enums.PayChannelEnum;
import com.baiye.redscarf.common.enums.PayMethodEnum;
import com.baiye.redscarf.common.enums.ResultCodeEnum;
import com.baiye.redscarf.common.result.Result;
import com.baiye.redscarf.common.util.JsonUtils;
import com.baiye.redscarf.common.util.OXMUtils;
import com.baiye.redscarf.common.util.XmlUtils;
import com.baiye.redscarf.pay.constant.WechatPayConstants;
import com.baiye.redscarf.pay.properties.WechatProperties;
import com.baiye.redscarf.pay.service.PayService;
import com.baiye.redscarf.pay.service.SignatureFactory;
import com.baiye.redscarf.pay.util.AbstractWebServiceUtils;
import com.baiye.redscarf.pay.util.XmlWebServiceHandler;
import com.baiye.redscarf.pay.wechat.WechatPayUnifiedOrderRequest;
import com.baiye.redscarf.pay.wechat.WechatPayUnifiedOrderResponse;
import com.baiye.redscarf.pay.wechat.WechatPrePayRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author baiye
 * @date 2021/3/12 2:54 下午
 **/
@Service
public class WechatPayServiceImpl implements PayService {

    private static final Logger log = LoggerFactory.getLogger(WechatPayServiceImpl.class);

    @Resource
    private WechatProperties wechatProperties;

    @Override
    public PayMethodEnum getPayMethod() {
        return PayMethodEnum.WECHAT_PAY;
    }

    @Override
    public WechatPayUnifiedOrderResponse unifiedPayOrder(WechatPrePayRequest request) {
        WechatPayUnifiedOrderRequest build = WechatPayUnifiedOrderRequest.builder()
                .appid(wechatProperties.getAppId())
                .mchId(wechatProperties.getMchId())
                .nonceStr("")
                .notifyUrl("")
                .outTradeNo(request.getOutTradeNo())
                .productId(request.getProductId())
                .spbillCreateIp(request.getIp())
                .tradeType(PayChannelEnum.WECHAT_NATIVE.getMethod())
                .totalFee(request.getAmount().movePointRight(2).intValue())
                .body("")
                .build();
        build.setSign(SignatureFactory.getSignature(PayChannelEnum.WECHAT_NATIVE).sign(build.toMap()));

        WechatPayUnifiedOrderResponse prepayId = getPrepayId(build);

        return prepayId;
    }

    public WechatPayUnifiedOrderResponse getPrepayId(WechatPayUnifiedOrderRequest request) {
        log.info(JSON.toJSONString(request));
        String responseData = AbstractWebServiceUtils.post(WechatPayConstants.ORDER_URL, request,
                new XmlWebServiceHandler<WechatPayUnifiedOrderRequest, String>(String.class) {
                    @Override
                    protected String parseResponseData(String responseData) {
                        return responseData;
                    }
                });
        log.info("【微信支付获取预支付订单】responseData：{}", responseData);
        WechatPayUnifiedOrderResponse response = OXMUtils.unmarshal(responseData, WechatPayUnifiedOrderResponse.class);
        if (null == response) {
            log.error("【微信支付获取预支付订单异常】response=null，request：{}", JsonUtils.toJson(request));
            throw Result.toBizException(ResultCodeEnum.WECHAT_PRE_PAY_ERROR);
        }
        if (!response.getReturnCode().equals(WechatPayConstants.SUCCESS)) {
            log.error("【微信支付获取预支付订单异常】returnCode=fail, response：{}", JsonUtils.toJson(response));
            throw Result.toBizException(ResultCodeEnum.WECHAT_PRE_PAY_ERROR);
        }
        if (!response.getResultCode().equals(WechatPayConstants.SUCCESS)) {
            log.error("【微信支付获取预支付订单异常】resultCode=fail, response：{}", JsonUtils.toJson(response));
            throw Result.toBizException(ResultCodeEnum.WECHAT_PRE_PAY_ERROR);
        }

        Map<String, String> parameters = XmlUtils.parse2Map(responseData);
        if (!SignatureFactory.getSignature(PayChannelEnum.WECHAT_NATIVE).verify(parameters)) {
            log.error("【微信支付获取预支付订单异常】签名无效，request：{}，responseData：{} ", JsonUtils.toJson(request), responseData);
            throw Result.toBizException(ResultCodeEnum.WECHAT_PRE_PAY_ERROR);
        }
        return response;
    }
}
