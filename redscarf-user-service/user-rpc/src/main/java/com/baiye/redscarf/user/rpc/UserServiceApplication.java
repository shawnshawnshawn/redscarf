package com.baiye.redscarf.user.rpc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author baiye
 * @date 2021/3/3 11:34 上午
 **/
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(value = "com.baiye.redscarf.user")
@MapperScan(basePackages = "com.baiye.redscarf.user.dao.mapper")
public class UserServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(UserServiceApplication.class, args);
        String userName = applicationContext.getEnvironment().getProperty("uname.a");
        String userAge = applicationContext.getEnvironment().getProperty("uname.b");
        System.err.println("user name :"+userName+"; age: "+userAge);
    }
}
