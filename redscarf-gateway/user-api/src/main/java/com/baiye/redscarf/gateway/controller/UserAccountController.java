package com.baiye.redscarf.gateway.controller;

import com.baiye.redscarf.common.result.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author baiye
 * @date 2021/3/3 10:40 上午
 **/
@RestController
@RequestMapping("/user")
public class UserAccountController {

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping(value = "/getUserAccountInfo/{id}", method = RequestMethod.GET)
    public String getUserAccountInfo(@PathVariable Long id) {
        String forObject = restTemplate.getForObject("http://localhost:8082/user/getUserAccount/"+id, String.class);
        return forObject;
    }
}
