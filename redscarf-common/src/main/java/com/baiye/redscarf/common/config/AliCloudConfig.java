package com.baiye.redscarf.common.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baiye.redscarf.common.properties.AliyunProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author baiye
 * @date 2021/4/20 11:43 上午
 **/
@Configuration
public class AliCloudConfig {

    @Resource
    private AliyunProperties aliyunProperties;

    @Bean
    public OSS ossClient() {
        return new OSSClientBuilder().build(aliyunProperties.getAliyunOss().getCdnEndpoint(), aliyunProperties.getAliyunOss().getAccessKeyId(), aliyunProperties.getAliyunOss().getAccessKeySecret());
    }

}
