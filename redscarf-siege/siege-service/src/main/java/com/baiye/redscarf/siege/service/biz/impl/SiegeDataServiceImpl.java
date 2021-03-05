package com.baiye.redscarf.siege.service.biz.impl;

import com.baiye.redscarf.common.enums.ResultCodeEnum;
import com.baiye.redscarf.common.result.Result;
import com.baiye.redscarf.siege.dao.entity.SiegeDataEntity;
import com.baiye.redscarf.siege.dao.mapper.SiegeDataMapper;
import com.baiye.redscarf.siege.service.biz.SiegeDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author baiye
 * @date 2021/3/4 5:05 下午
 **/
@Service
public class SiegeDataServiceImpl implements SiegeDataService {

    @Resource
    private SiegeDataMapper siegeDataMapper;

    @Override
    public SiegeDataEntity getSiegeDataById(Long id) {
        SiegeDataEntity siegeDataEntity = siegeDataMapper.selectBySiegeId(id);
        return siegeDataEntity;
    }
}
