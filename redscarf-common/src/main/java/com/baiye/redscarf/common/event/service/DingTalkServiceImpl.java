package com.baiye.redscarf.common.event.service;

import com.alibaba.fastjson.JSON;
import com.baiye.redscarf.common.constants.CacheConstants;
import com.baiye.redscarf.common.event.NotifyMessage;
import com.baiye.redscarf.common.properties.AliyunProperties;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiChatSendRequest;
import com.dingtalk.api.response.OapiChatSendResponse;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.taobao.api.ApiException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author 白也
 * @since  2021/08/19 2:27 下午
 */
@Service
public class DingTalkServiceImpl implements DingTalkService {

    private static final Logger log = LoggerFactory.getLogger(DingTalkServiceImpl.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private AliDingTalkOpenApiService dingTalkOpenService;

    @Resource
    private AliyunProperties aliyunProperties;

    @Override
    public void sendMessage(NotifyMessage notifyMessage) {
        String accessToken = getAccessToken();
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/chat/send");
        OapiChatSendRequest request = new OapiChatSendRequest();
        request.setChatid(notifyMessage.getChatId());
        OapiChatSendRequest.Msg msg = new OapiChatSendRequest.Msg();
        msg.setMsgtype("text");
        OapiChatSendRequest.Text text = new OapiChatSendRequest.Text();
        text.setContent(notifyMessage.getMessage());
        msg.setText(text);
        request.setMsg(msg);
        try {
            OapiChatSendResponse response = client.execute(request, accessToken);
            log.info("res -> {}", JSON.toJSONString(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private String getAccessToken() {
        String token = stringRedisTemplate.opsForValue().get(CacheConstants.DING_TALK_ACCESS_TOKEN);
        if (StringUtils.isNotBlank(token)) {
            return token;
        }

        try {
            Response<OapiGettokenResponse> execute = dingTalkOpenService.getToken(aliyunProperties.getDingTalk().getAppKey(), aliyunProperties.getDingTalk().getAppSecret()).execute();
            if (execute.isSuccessful()) {
                OapiGettokenResponse body = execute.body();
                log.info("access_token -> {}", JSON.toJSONString(body));
                if (body != null) {
                    stringRedisTemplate.opsForValue().set(CacheConstants.DING_TALK_ACCESS_TOKEN, body.getAccessToken(), body.getExpiresIn() == null ? 3600 : body.getExpiresIn() - 10, TimeUnit.SECONDS);
                    return body.getAccessToken();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
