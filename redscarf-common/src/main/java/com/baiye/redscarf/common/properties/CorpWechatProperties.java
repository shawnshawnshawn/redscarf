package com.baiye.redscarf.common.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "wechat.corp")
public class CorpWechatProperties {
    private String corpId;

    private String secret;
}
