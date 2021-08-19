package com.baiye.redscarf.common.event;

import com.baiye.redscarf.common.event.service.CompanyWechatService;
import com.baiye.redscarf.common.event.service.DingTalkService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author baiye
 * @since 2021/8/19 3:05 下午
 **/
@Component
public class NotifyListener implements ApplicationListener<NotifyEvent> {

    @Resource
    private DingTalkService dingTalkService;

    @Resource
    private CompanyWechatService companyWechatService;

    @Override
    public void onApplicationEvent(NotifyEvent event) {
        NotifyMessage notifyMessage = event.getNotifyMessage();
        switch (notifyMessage.getNotifyTypeEnum()) {
            case ALI_DING:
                dingTalkService.sendMessage(notifyMessage);
                break;
            case CORP_WECHAT:
                companyWechatService.sendMessage(notifyMessage);
            default:
                break;
        }
    }
}
