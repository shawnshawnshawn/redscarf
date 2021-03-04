package com.baiye.redscarf.gateway.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baiye.redscarf.gateway.fallback.UserFeignFailback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author baiye
 * @date 2021/3/3 6:27 下午
 **/
@FeignClient(value = "redscarf-user-service-dev", fallback = UserFeignFailback.class)
public interface UserFeignService {

    @RequestMapping(value = "/user/getUserAccount/{id}", method = RequestMethod.GET)
    String getUserAccountInfo(@PathVariable("id") Long id);
}
