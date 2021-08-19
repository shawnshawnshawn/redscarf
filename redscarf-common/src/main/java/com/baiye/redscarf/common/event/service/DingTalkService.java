package com.baiye.redscarf.common.event.service;


import com.baiye.redscarf.common.event.NotifyMessage;

/**
 * @author 白也
 * @since  2021/08/19 2:27 下午
 */
public interface DingTalkService {

    void sendMessage(NotifyMessage notifyMessage);
}
