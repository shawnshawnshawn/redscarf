package com.baiye.redscarf.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author baiye
 * @date 2021/3/3 6:26 下午
 **/
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class RedscarfGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedscarfGatewayApplication.class, args);
    }
}
