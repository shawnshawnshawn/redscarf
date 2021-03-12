package com.baiye.redscarf.pay.service;

import com.alibaba.csp.sentinel.util.AssertUtil;
import com.baiye.redscarf.common.enums.PayMethodEnum;
import com.baiye.redscarf.common.enums.ResultCodeEnum;
import com.baiye.redscarf.common.result.Result;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author baiye
 * @date 2021/3/12 2:56 下午
 **/
@Component
public class PayServiceFactory {

    private Map<PayMethodEnum, PayService> map;

    @Resource
    private List<PayService> payServices;

    @PostConstruct
    public void init() {
        AssertUtil.notNull(payServices, "PayService must not be null");
        this.map = new HashMap<>(payServices.size());
        for (PayService payService : payServices) {
            map.put(payService.getPayMethod(), payService);
        }
    }

    public PayService getPayService(PayMethodEnum payMethodEnum) {
        PayService payService = map.get(payMethodEnum);
        if (payService == null) {
            throw Result.toBizException(ResultCodeEnum.PAY_SERVICE_NOT_EXIST);
        }
        return payService;
    }
}
