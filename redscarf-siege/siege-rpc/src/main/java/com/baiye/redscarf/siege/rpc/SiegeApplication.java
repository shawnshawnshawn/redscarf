package com.baiye.redscarf.siege.rpc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author baiye
 * @date 2021/3/4 5:08 下午
 **/
@EnableFeignClients(basePackages = "com.baiye.redscarf.siege.service.reference.feign")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.baiye.redscarf.siege")
@MapperScan(basePackages = "com.baiye.redscarf.siege.dao.mapper")
@EnableElasticsearchRepositories(basePackages = "com.baiye.redscarf.siege.dao.elastic")
public class SiegeApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SiegeApplication.class, args);
        String es = applicationContext.getEnvironment().getProperty("spring.data.elasticsearch.repositories.enabled");
        System.err.println("es :"+es);
    }
}
