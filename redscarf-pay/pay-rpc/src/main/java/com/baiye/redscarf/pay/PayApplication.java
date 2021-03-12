package com.baiye.redscarf.pay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author baiye
 * @date 2021/3/11 5:13 下午
 **/
@EnableDiscoveryClient
@MapperScan(basePackages = "com.baiye.redscarf.pay.dao.mapper")
@SpringBootApplication(scanBasePackages = "com.baiye.redscarf.pay")
public class PayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }
}
