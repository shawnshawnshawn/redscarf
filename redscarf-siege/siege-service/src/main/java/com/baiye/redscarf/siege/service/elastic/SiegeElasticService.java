package com.baiye.redscarf.siege.service.elastic;

import com.baiye.redscarf.siege.dao.elastic.SiegeElastic;

/**
 * @author baiye
 * @date 2021/3/7 1:03 上午
 **/
public interface SiegeElasticService {
    SiegeElastic findById(Long id);
}
