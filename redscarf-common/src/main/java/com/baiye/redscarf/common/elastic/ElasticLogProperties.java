package com.baiye.redscarf.common.elastic;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author baiye
 * @since 2021/6/5 3:01 下午
 **/
@Data
@Component
@ConfigurationProperties(prefix = "es.log")
public class ElasticLogProperties {

    /**
     * 项目名称
     */
    private String projectName = "work2";

    /**
     * es链接（目前仅支持单机es）
     */
    private String uri = "http://8.210.148.61:8090/";

    /**
     * app用户
     */
    private String appKey;

    /**
     * app用户秘钥
     */
    private String secret;

    private String env;
}
