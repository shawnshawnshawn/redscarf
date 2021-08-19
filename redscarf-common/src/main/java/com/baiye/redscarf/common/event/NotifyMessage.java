package com.baiye.redscarf.common.event;

import com.baiye.redscarf.common.enums.NotifyTypeEnum;
import lombok.Data;

/**
 * @author baiye
 * @since 2021/8/19 2:57 下午
 **/
@Data
public class NotifyMessage {

    private NotifyTypeEnum notifyTypeEnum;

    private String chatId;

    private String message;
}
