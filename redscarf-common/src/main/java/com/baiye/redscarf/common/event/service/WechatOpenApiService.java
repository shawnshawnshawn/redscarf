package com.baiye.redscarf.common.event.service;

import com.dingtalk.api.response.OapiGettokenResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

/**
 * @author baiye
 * @since 2021/8/19 3:06 下午
 **/
public interface WechatOpenApiService {

    @GET("gettoken")
    Call<OapiGettokenResponse> getToken(@Query("corpid") String corpid, @Query("corpsecret") String corpsecret);

    @POST("appchat/create")
    Call<CreateTopicResult> createTopic(@Query("access_token") String access_token, @Body Topic topic);

    @POST("appchat/send")
    Call<BaseResult> sendMessage(@Query("access_token") String access_token, @Body WechatMessage message);

    @POST("appchat/update")
    Call<BaseResult> updateTopic(@Query("access_token") String access_token, @Body UpdateTopic topic);

    @Data
    class UpdateTopic{

        private String chatid;

        private String name;

        private String owner;

        private List<String> del_user_list;

        private List<String> add_user_list;
    }

    @Data
    class Topic{
        private String name;

        private String owner;

        private List<String> userlist;
    }

    @Data
    class BaseResult{
        private Integer errcode;

        private String errmsg;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    class CreateTopicResult extends BaseResult {

        private String chatid;
    }

    @Data
    class WechatMessage {

        private String chatid;

        private String msgtype;

        private Integer safe;

        private Text text;


    }

    @Data
    class Text{
        private String content;
    }
}
