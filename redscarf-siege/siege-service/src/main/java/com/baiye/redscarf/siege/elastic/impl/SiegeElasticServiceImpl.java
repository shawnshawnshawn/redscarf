package com.baiye.redscarf.siege.elastic.impl;

import com.baiye.redscarf.common.enums.ResultCodeEnum;
import com.baiye.redscarf.common.result.Result;
import com.baiye.redscarf.siege.elastic.SiegeElastic;
import com.baiye.redscarf.siege.elastic.SiegeRepository;
import com.baiye.redscarf.siege.elastic.SiegeElasticService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author baiye
 * @date 2021/3/7 1:04 上午
 **/
@Service
public class SiegeElasticServiceImpl implements SiegeElasticService {

    @Resource
    private SiegeRepository siegeRepository;

    @Override
    public SiegeElastic findById(Long id) {
        Optional<SiegeElastic> byId = siegeRepository.findById(id);
        if (!byId.isPresent()) throw Result.toBizException(ResultCodeEnum.ELASTIC_RESULT_NULL);
        return  byId.get();
    }
}
