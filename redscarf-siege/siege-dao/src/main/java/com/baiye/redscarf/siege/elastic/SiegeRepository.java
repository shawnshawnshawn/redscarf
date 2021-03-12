package com.baiye.redscarf.siege.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author baiye
 * @date 2021/3/7 12:54 上午
 **/
public interface SiegeRepository extends ElasticsearchRepository<SiegeElastic,Long> {
}
