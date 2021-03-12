package com.baiye.redscarf.siege.reference.fallback;

import com.baiye.redscarf.common.enums.ResultCodeEnum;
import com.baiye.redscarf.common.result.Result;
import com.baiye.redscarf.siege.reference.feign.UserFeignService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author baiye
 * @date 2021/3/4 5:15 下午
 **/
@Component
@RequestMapping("/fallback")
public class UserFeignFallback implements UserFeignService {

    private static final String result = Result.ofFail(ResultCodeEnum.SERVER_UNUSERFUL).toString();

    @Override
    public String getUserAccountInfo(Long id) {
        return result;
    }

}
