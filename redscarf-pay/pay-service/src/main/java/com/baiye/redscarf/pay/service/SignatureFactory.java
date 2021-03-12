package com.baiye.redscarf.pay.service;

import com.baiye.redscarf.common.enums.PayChannelEnum;
import com.baiye.redscarf.common.enums.ResultCodeEnum;
import com.baiye.redscarf.common.result.Result;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author baiye
 * @date 2021/3/12 10:50 上午
 **/
@Component
public class SignatureFactory implements ApplicationContextAware {

    private static final Map<PayChannelEnum, SignatureService> SIGNATURE = new HashMap<>(10);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, SignatureService> map = applicationContext.getBeansOfType(SignatureService.class);
        map.forEach((k,v) -> SIGNATURE.put(v.getChannel(), v));
    }

    public static SignatureService getSignature(PayChannelEnum payChannelEnum) {
        SignatureService signatureService = SIGNATURE.get(payChannelEnum);
        if (signatureService == null) {
            throw Result.toBizException(ResultCodeEnum.SIGNATURE_SERVICE_NOT_EXIST);
        }
        return signatureService;
    }
}
