package com.baiye.redscarf.common.properties;

import com.baiye.redscarf.common.properties.pojo.AliyunOss;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author baiye
 * @since 2021/8/9 11:58 上午
 **/
@Data
@Component
@ConfigurationProperties(prefix = "aliyun")
public class AliyunProperties {

    private AliyunOss aliyunOss;
}
