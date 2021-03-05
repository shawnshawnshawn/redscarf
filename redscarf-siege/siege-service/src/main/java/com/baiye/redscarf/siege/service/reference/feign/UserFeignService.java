package com.baiye.redscarf.siege.service.reference.feign;

import com.baiye.redscarf.siege.service.reference.fallback.UserFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author baiye
 * @date 2021/3/4 5:14 下午
 **/
@FeignClient(value = "redscarf-user-service-dev", fallback = UserFeignFallback.class)
public interface UserFeignService {

    @RequestMapping(value = "/user-server/user/getUserAccount/{id}", method = RequestMethod.GET)
    String getUserAccountInfo(@PathVariable("id") Long id);
}
