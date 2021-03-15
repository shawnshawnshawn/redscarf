package com.baiye.redscarf.web.service.impl;

import com.baiye.redscarf.common.result.PageResult;
import com.baiye.redscarf.common.result.Result;
import com.baiye.redscarf.common.util.DataConverterUtils;
import com.baiye.redscarf.common.util.JsonUtils;
import com.baiye.redscarf.web.common.form.SiegeListForm;
import com.baiye.redscarf.web.common.vo.SiegeListVo;
import com.baiye.redscarf.web.reference.SiegeFeignService;
import com.baiye.redscarf.web.service.FeigeSiegeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author baiye
 * @date 2021/3/15 2:38 下午
 **/
@Service
public class FeigeSiegeServiceImpl implements FeigeSiegeService {

    private static final Logger log = LoggerFactory.getLogger(FeigeSiegeServiceImpl.class);

    @Resource
    private SiegeFeignService siegeFeignService;

    @Override
    public PageResult<SiegeListVo> listSiegePage(String siegeType, SiegeListForm form) {
        String pageResult = siegeFeignService.listSiegePage(siegeType, form);
        log.warn("siege info -> {}", pageResult);
        PageResult<SiegeListVo> res = DataConverterUtils.convertPageResultObject(pageResult);
        if (!res.isSuccess()) {
            throw Result.toBizException(res.getCode(), res.getMessage());
        }
        log.warn("data converter siege info -> {}", JsonUtils.toJson(res));
        return res;
    }
}
