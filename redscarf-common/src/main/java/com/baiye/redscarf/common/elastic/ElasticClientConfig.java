package com.baiye.redscarf.common.elastic;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author baiye
 * @since 2021/6/4 6:27 下午
 **/
@Configuration
public class ElasticClientConfig {

    @Resource
    private ElasticLogProperties elasticLogProperties;

    @Bean
    public HttpClientConnectionManager httpClientConnectionManager() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(500);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(50);
        return poolingHttpClientConnectionManager;
    }

    @Bean
    public HttpClient httpClient(HttpClientConnectionManager httpClientConnectionManager) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(httpClientConnectionManager);
        return httpClientBuilder.build();
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory(HttpClient httpClient) {
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpComponentsClientHttpRequestFactory.setHttpClient(httpClient);
        httpComponentsClientHttpRequestFactory.setConnectTimeout(20 * 1000);
        httpComponentsClientHttpRequestFactory.setReadTimeout(20 * 1000);
        httpComponentsClientHttpRequestFactory.setConnectionRequestTimeout(20 * 1000);
        return httpComponentsClientHttpRequestFactory;
    }

    @Bean("restTemplateForEs")
    public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(clientHttpRequestFactory);
        return restTemplate;
    }

    @Bean
    public ElasticClient elasticClient(@Qualifier("restTemplateForEs") RestTemplate restTemplate) {
        return new ElasticClient.Builder()
                .restTemplate(restTemplate)
                .uri(elasticLogProperties.getUri())
                .project(elasticLogProperties.getProjectName())
                .env(elasticLogProperties.getEnv())
                .build();
    }
}
