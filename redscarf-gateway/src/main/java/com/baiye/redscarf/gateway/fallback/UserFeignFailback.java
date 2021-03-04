package com.baiye.redscarf.gateway.fallback;

import com.baiye.redscarf.common.enums.ResultCodeEnum;
import com.baiye.redscarf.common.result.Result;
import com.baiye.redscarf.gateway.service.UserFeignService;
import org.springframework.stereotype.Component;

/**
 * @author baiye
 * @date 2021/3/3 6:28 下午
 **/
@Component
public class UserFeignFailback implements UserFeignService {
    @Override
    public String getUserAccountInfo(Long id) {
        return result;
    }

    private static final String result = Result.ofFail(ResultCodeEnum.SERVER_UNUSERFUL).toString();
}
