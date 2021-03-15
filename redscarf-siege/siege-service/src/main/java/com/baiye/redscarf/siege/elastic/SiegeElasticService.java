package com.baiye.redscarf.siege.elastic;

import com.baiye.redscarf.common.result.PageVo;
import com.baiye.redscarf.siege.elastic.SiegeElastic;
import com.baiye.redscarf.siege.vo.SiegeListVo;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;

/**
 * @author baiye
 * @date 2021/3/7 1:03 上午
 **/
public interface SiegeElasticService {
    SiegeElastic findById(Long id);

    PageVo<SiegeListVo> search(NativeSearchQuery searchQueryBuilder);

}
