package com.baiye.redscarf.common.event.service;

import com.baiye.redscarf.common.event.NotifyMessage;
import com.baiye.redscarf.common.event.param.CreateTopicParam;
import com.baiye.redscarf.common.event.param.UpdateTopicParam;

/**
 * @author baiye
 * @date 2021/4/13 9:56 上午
 **/
public interface CompanyWechatService {

    String sendMessage(NotifyMessage wechatMessageParam);

    String createTopic(CreateTopicParam param);

    void updateTopic(UpdateTopicParam form);
}
