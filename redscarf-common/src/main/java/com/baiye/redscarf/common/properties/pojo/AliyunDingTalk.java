package com.baiye.redscarf.common.properties.pojo;

import lombok.Data;

/**
 * @author baiye
 * @since 2021/8/19 3:19 下午
 **/
@Data
public class AliyunDingTalk {

    private String appSysName;

    private String appKey;

    private String appSecret;

    private String agentId;

    private String corpId;

    private String env;

    private String errorChatId;

    private String notifyChatId;
}
