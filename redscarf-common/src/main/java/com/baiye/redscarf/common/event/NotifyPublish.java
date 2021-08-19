package com.baiye.redscarf.common.event;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author baiye
 * @since 2021/8/19 3:02 下午
 **/
@Component
public class NotifyPublish {

    @Resource
    private ApplicationContext applicationContext;

    public void publish(NotifyEvent event) {
        applicationContext.publishEvent(event);
    }
}
