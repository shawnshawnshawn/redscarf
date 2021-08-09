package com.baiye.redscarf.common.properties.pojo;

import lombok.Data;

/**
 * @author baiye
 * @since 2021/8/9 12:05 下午
 **/
@Data
public class AliyunOss {

    private String bucketName;

    private String endpoint;

    private String cdnEndpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String fileHost;

    private String fileUrl;

    private String cdnFileUrl;

    private String imageBucket;

    private String videoBucket;

    private String infoBucket;

    private String qrBucket;
}
