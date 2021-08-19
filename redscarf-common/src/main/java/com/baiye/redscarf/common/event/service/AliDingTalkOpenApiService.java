package com.baiye.redscarf.common.event.service;

import com.dingtalk.api.response.OapiGettokenResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author baiye
 * @since 2021/8/19 3:06 下午
 **/
public interface AliDingTalkOpenApiService {

    @GET("gettoken")
    Call<OapiGettokenResponse> getToken(@Query("appkey") String appKey, @Query("appsecret") String appSec);
}
