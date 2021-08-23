package com.baiye.redscarf.common.event.service;

import com.alibaba.fastjson.JSON;
import com.baiye.redscarf.common.constants.CacheConstants;
import com.baiye.redscarf.common.event.NotifyMessage;
import com.baiye.redscarf.common.event.param.CreateTopicParam;
import com.baiye.redscarf.common.event.param.UpdateTopicParam;
import com.baiye.redscarf.common.properties.CorpWechatProperties;
import com.dingtalk.api.response.OapiGettokenResponse;
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
 * @author baiye
 * @since  2021/4/13 9:56 上午
 **/
@Service
public class CompanyWechatServiceImpl implements CompanyWechatService {

    private static final Logger log = LoggerFactory.getLogger(CompanyWechatServiceImpl.class);

    @Resource
    private WechatOpenApiService wechatOpenApiService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private CorpWechatProperties corpWechatProperties;

    @Override
    public String sendMessage(NotifyMessage notifyMessage) {
        WechatOpenApiService.WechatMessage message = new WechatOpenApiService.WechatMessage();
        message.setChatid(notifyMessage.getChatId());
        message.setSafe(0);
        message.setMsgtype("text");
        WechatOpenApiService.Text text = new WechatOpenApiService.Text();
        text.setContent(notifyMessage.getMessage());
        message.setText(text);
        try {
            Response<WechatOpenApiService.BaseResult> execute = wechatOpenApiService.sendMessage(getAccessToken(), message).execute();
            if (execute.isSuccessful()) {
                WechatOpenApiService.BaseResult body = execute.body();
                log.info("body -> {}", JSON.toJSONString(body));
                if (body != null) {
                    return body.getErrmsg();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "fail";
    }

    @Override
    public String createTopic(CreateTopicParam param) {
        WechatOpenApiService.Topic topic = new WechatOpenApiService.Topic();
        topic.setName(param.getName());
        topic.setUserlist(param.getUserlist());
        topic.setOwner(param.getOwner());
        try {
            Response<WechatOpenApiService.CreateTopicResult> execute = wechatOpenApiService.createTopic(getAccessToken(), topic).execute();
            if (execute.isSuccessful()) {
                WechatOpenApiService.CreateTopicResult body = execute.body();
                log.info("body -> {}", JSON.toJSONString(body));
                if (body != null) {
                    return body.getChatid();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void updateTopic(UpdateTopicParam form) {
        WechatOpenApiService.UpdateTopic topic = new WechatOpenApiService.UpdateTopic();
        topic.setChatid(form.getChatid());
        topic.setAdd_user_list(form.getAddUserList());
        topic.setDel_user_list(form.getDelUserList());
        topic.setName(form.getName());
        topic.setOwner(form.getOwner());
        try {
            Response<WechatOpenApiService.BaseResult> execute = wechatOpenApiService.updateTopic(getAccessToken(), topic).execute();
            if (execute.isSuccessful()) {
                WechatOpenApiService.BaseResult body = execute.body();
                if (body != null) {
                    if (!body.getErrmsg().equals("ok")) {
                        throw new RuntimeException("修改企业微信回话信息错误" + body.getErrmsg());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized String getAccessToken() {
        String token = stringRedisTemplate.opsForValue().get(CacheConstants.COMPANY_WECHAT_ACCESS_TOKEN);
        if (StringUtils.isNotBlank(token)) {
            return token;
        }

        try {
            Response<OapiGettokenResponse> execute = wechatOpenApiService.getToken(corpWechatProperties.getCorpId(), corpWechatProperties.getSecret()).execute();
            if (execute.isSuccessful()) {
                OapiGettokenResponse body = execute.body();
                log.info("wechat access_token -> {}", JSON.toJSONString(body));
                if (body != null) {
                    stringRedisTemplate.opsForValue().set(CacheConstants.COMPANY_WECHAT_ACCESS_TOKEN, body.getAccessToken(), body.getExpiresIn() == null ? 3600 : body.getExpiresIn() - 10, TimeUnit.SECONDS);
                    return body.getAccessToken();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
