package com.baiye.redscarf.web.reference;

import com.baiye.redscarf.web.common.form.SiegeListForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

/**
 * @author baiye
 * @date 2021/3/15 2:31 下午
 **/
@FeignClient(value = "redscarf-siege-service-dev")
@RequestMapping("/siege-server/siege/")
public interface SiegeFeignService {

    @RequestMapping(value = "/listSiegePage/{siegeType}", method = RequestMethod.GET)
    String listSiegePage(@PathVariable("siegeType") String siegeType, @SpringQueryMap SiegeListForm form);
}
