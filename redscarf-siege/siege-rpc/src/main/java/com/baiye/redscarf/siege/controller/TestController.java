package com.baiye.redscarf.siege.controller;

import com.baiye.redscarf.siege.reference.feign.UserFeignService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author baiye
 * @date 2021/3/4 5:26 下午
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private UserFeignService userFeignService;

    @RequestMapping(value = "/test1/{id}", method = RequestMethod.GET)
    public String test1(@PathVariable("id") Long id) {
        return userFeignService.getUserAccountInfo(id);
    }
}
