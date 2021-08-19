package com.baiye.redscarf.common.event.param;

import lombok.Data;

/**
 * @author baiye
 * @date 2021/4/13 10:01 上午
 **/
@Data
public class WechatMessageParam {

    private String message;

    private String chatId;
}
