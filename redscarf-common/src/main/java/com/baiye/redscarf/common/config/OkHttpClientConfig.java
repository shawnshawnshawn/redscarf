package com.baiye.redscarf.common.config;

import com.baiye.redscarf.common.event.service.AliDingTalkOpenApiService;
import com.baiye.redscarf.common.event.service.WechatOpenApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author baiye
 * @since 2021/8/19 3:05 下午
 **/

@Configuration
public class OkHttpClientConfig {

    private static final Logger log = LoggerFactory.getLogger(OkHttpClientConfig.class);

    @Bean
    public OkHttpClient build() {
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addNetworkInterceptor(new LoggingInterceptor())
                .retryOnConnectionFailure(true)
                .connectionPool(new ConnectionPool(100, 5, TimeUnit.MINUTES))
                .hostnameVerifier((hostName, session) -> true)
                .cookieJar(new CookieJar() {
                    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put(HttpUrl.parse(url.username()), cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(HttpUrl.parse(url.host()));
                        return cookies != null ? cookies : new ArrayList<>();
                    }
                })
                .build();
    }

    private static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long start = System.nanoTime();
            log.info("Sending Request [Url {}, Method {}, RequestBody {}] on {}.",
                    request.url(), request.method(), request.body(), chain.connection());
            log.debug("Request Headers is {}", request.headers());
            Response response = chain.proceed(request);
            log.info("Received {} in {}ms.",
                    response, (System.nanoTime() - start) / 1e6d);
            log.debug("Response Headers is {}", response.headers());
            return response;
        }
    }

    @Bean
    public Retrofit dingTalkOpenRetrofit(@Autowired OkHttpClient okHttpClient) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        return new Retrofit.Builder()
                .baseUrl("https://oapi.dingtalk.com/")
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .client(okHttpClient)
                .build();
    }

    @Bean
    public AliDingTalkOpenApiService dingTalkOpenService(@Autowired @Qualifier("dingTalkOpenRetrofit") Retrofit dingTalkOpenRetrofit) {
        return dingTalkOpenRetrofit.create(AliDingTalkOpenApiService.class);
    }

    @Bean
    public Retrofit companyWechatOpenRetrofit(@Autowired OkHttpClient okHttpClient) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        return new Retrofit.Builder()
                .baseUrl("https://qyapi.weixin.qq.com/cgi-bin/")
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .client(okHttpClient)
                .build();
    }

    @Bean
    public WechatOpenApiService companyWechatOpenService(@Autowired @Qualifier("companyWechatOpenRetrofit") Retrofit companyWechatOpenRetrofit) {
        return companyWechatOpenRetrofit.create(WechatOpenApiService.class);
    }
}
