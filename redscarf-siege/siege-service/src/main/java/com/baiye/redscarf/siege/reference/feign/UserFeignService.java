package com.baiye.redscarf.siege.reference.feign;

import com.baiye.redscarf.siege.reference.fallback.UserFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author baiye
 * @date 2021/3/4 5:14 下午
 **/
@FeignClient(value = "redscarf-user-service-dev", fallback = UserFeignFallback.class)
@RequestMapping("/user-server/user")
public interface UserFeignService {

    @RequestMapping(value = "/getUserAccount/{id}", method = RequestMethod.GET)
    String getUserAccountInfo(@PathVariable("id") Long id);
}
