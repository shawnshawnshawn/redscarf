package com.baiye.redscarf.pay.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author baiye
 * @date 2021/3/12 1:34 下午
 **/
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatProperties {

    private String appId;

    private String secret;

    private String mchId;

    private String apiKey;
}
