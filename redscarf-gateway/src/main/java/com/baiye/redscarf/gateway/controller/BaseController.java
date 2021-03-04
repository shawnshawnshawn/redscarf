package com.baiye.redscarf.gateway.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.baiye.redscarf.common.enums.ResultCodeEnum;
import com.baiye.redscarf.common.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author baiye
 * @date 2021/3/4 1:13 下午
 **/
public class BaseController {

    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    public String blockHandler(@PathVariable("id") Long id, BlockException blockException){
        log.warn("触发流控！！！！！！！！");
        return Result.ofError(ResultCodeEnum.SERVER_UNUSERFUL).toString();
    }
}
