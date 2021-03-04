package com.baiye.redscarf.gateway.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baiye.redscarf.gateway.service.UserFeignService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author baiye
 * @date 2021/3/3 10:40 上午
 **/
@RestController
@RequestMapping("/user")
public class UserAccountController extends BaseController{

    @Resource
    private UserFeignService userFeignService;

    @SentinelResource(value = "getUserAccountInfo", blockHandler = "blockHandler")
    @RequestMapping(value = "/getUserAccountInfo/{id}", method = RequestMethod.GET)
    public String getUserAccountInfo(@PathVariable("id") Long id) {
        return userFeignService.getUserAccountInfo(id);
    }
}
