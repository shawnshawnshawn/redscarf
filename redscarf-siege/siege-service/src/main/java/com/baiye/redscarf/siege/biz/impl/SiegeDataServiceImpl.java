package com.baiye.redscarf.siege.biz.impl;

import com.baiye.redscarf.siege.entity.SiegeDataEntity;
import com.baiye.redscarf.siege.mapper.SiegeDataMapper;
import com.baiye.redscarf.siege.biz.SiegeDataService;
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
