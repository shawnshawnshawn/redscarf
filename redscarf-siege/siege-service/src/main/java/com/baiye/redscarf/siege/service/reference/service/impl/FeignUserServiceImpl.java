package com.baiye.redscarf.siege.service.reference.service.impl;

import com.baiye.redscarf.common.result.Result;
import com.baiye.redscarf.common.util.DataConverterUtils;
import com.baiye.redscarf.common.util.JsonUtils;
import com.baiye.redscarf.siege.service.reference.feign.UserFeignService;
import com.baiye.redscarf.siege.service.reference.service.FeignUserService;
import com.baiye.redscarf.siege.common.dto.UserAccountDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author baiye
 * @date 2021/3/4 6:17 下午
 **/
@Service
public class FeignUserServiceImpl implements FeignUserService {

    private static final Logger log = LoggerFactory.getLogger(FeignUserServiceImpl.class);

    @Resource
    private UserFeignService userFeignService;

    @Override
    public UserAccountDto getUserAccountInfo(Long id) {
        String result = userFeignService.getUserAccountInfo(id);
        log.warn("user info -> {}", result);
        Result<UserAccountDto> res = DataConverterUtils.convertResultObject(result, UserAccountDto.class);
        if (!res.isSuccess()) {
            throw Result.toBizException(res.getCode(), res.getMessage());
        }
        log.warn("data converter user info -> {}", JsonUtils.toJson(res));
        return res.getData();
    }
}
