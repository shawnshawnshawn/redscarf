package com.baiye.redscarf.pay.service.impl;

import com.alibaba.fastjson.JSON;
import com.baiye.redscarf.common.enums.PayChannelEnum;
import com.baiye.redscarf.pay.constant.WechatPayConstants;
import com.baiye.redscarf.pay.properties.WechatProperties;
import com.baiye.redscarf.pay.service.SignatureService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author baiye
 * @date 2021/3/12 10:57 上午
 **/
@Service
public class WechatGeneralSignatureServiceImpl implements SignatureService {

    @Resource
    private WechatProperties wechatProperties;

    @Override
    public PayChannelEnum getChannel() {
        return PayChannelEnum.WECHAT_JSAPI;
    }

    @Override
    public String sign(Map<String, String> parameters) {
        ArrayList<String> list = new ArrayList<>();
        parameters.forEach((k, v) -> {
            if (StringUtils.isNotBlank(k) && StringUtils.isNotBlank(v)) {
                list.add(k + "=" + v);
            }
        });
        list.sort(String.CASE_INSENSITIVE_ORDER);
        list.add("key" + "=" + wechatProperties.getApiKey());
        return StringUtils.upperCase(DigestUtils.md5Hex(StringUtils.join(list, "&")));
    }

    @Override
    public Boolean verify(Map<String, String> parameters) {
        String sign = parameters.remove(WechatPayConstants.SIGN);
        return StringUtils.equals(sign(parameters), sign);
    }
}
