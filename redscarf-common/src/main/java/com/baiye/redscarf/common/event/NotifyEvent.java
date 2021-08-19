package com.baiye.redscarf.common.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author baiye
 * @since 2021/8/19 3:00 下午
 **/
public class NotifyEvent extends ApplicationEvent {

    private  NotifyMessage notifyMessage;

    public NotifyEvent(Object source, NotifyMessage notifyMessage) {
        super(source);
        this.notifyMessage = notifyMessage;
    }

    public NotifyMessage getNotifyMessage() {
        return notifyMessage;
    }

    public void setNotifyMessage(NotifyMessage notifyMessage) {
        this.notifyMessage = notifyMessage;
    }
}
