package com.baiye.redscarf.item;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author baiye
 * @date 2021/3/7 12:06 上午
 **/
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.baiye.redscarf.item.reference.feign")
@MapperScan(basePackages = "com.baiye.redscarf.item.mapper")
@SpringBootApplication(scanBasePackages = "com.baiye.redscarf.item")
public class ItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class, args);
    }
}
