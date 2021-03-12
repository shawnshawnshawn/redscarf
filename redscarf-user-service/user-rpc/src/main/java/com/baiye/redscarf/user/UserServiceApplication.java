package com.baiye.redscarf.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author baiye
 * @date 2021/3/3 11:34 上午
 **/
@EnableFeignClients(basePackages = "com.baiye.redscarf.user.reference.feign")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.baiye.redscarf.user")
@MapperScan(basePackages = "com.baiye.redscarf.user.mapper")
public class UserServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(UserServiceApplication.class, args);
        String userName = applicationContext.getEnvironment().getProperty("uname.a");
        String userAge = applicationContext.getEnvironment().getProperty("uname.b");
        System.err.println("user name :"+userName+"; age: "+userAge);
    }
}
