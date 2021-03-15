package com.baiye.redscarf.siege.elastic.impl;

import com.baiye.redscarf.common.enums.ResultCodeEnum;
import com.baiye.redscarf.common.result.PageVo;
import com.baiye.redscarf.common.result.Result;
import com.baiye.redscarf.common.util.DateUtils;
import com.baiye.redscarf.common.util.JsonUtils;
import com.baiye.redscarf.siege.elastic.SiegeElastic;
import com.baiye.redscarf.siege.elastic.SiegeRepository;
import com.baiye.redscarf.siege.elastic.SiegeElasticService;
import com.baiye.redscarf.siege.vo.SiegeListVo;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author baiye
 * @date 2021/3/7 1:04 上午
 **/
@Service
public class SiegeElasticServiceImpl implements SiegeElasticService {

    private static final Logger log = LoggerFactory.getLogger(SiegeElasticServiceImpl.class);

    @Resource
    private SiegeRepository siegeRepository;

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public SiegeElastic findById(Long id) {
        Optional<SiegeElastic> byId = siegeRepository.findById(id);
        if (!byId.isPresent()) throw Result.toBizException(ResultCodeEnum.ELASTIC_RESULT_NULL);
        return byId.get();
    }

    @Override
    public PageVo<SiegeListVo> search(NativeSearchQuery searchQueryBuilder) {
        SearchHits<SiegeElastic> search = elasticsearchRestTemplate.search(searchQueryBuilder, SiegeElastic.class);
        log.info("siege elastic search1 -> {}", JsonUtils.toJson(search));
        int count = Math.toIntExact(search.getTotalHits());
        List<SiegeListVo> vos = new ArrayList<>();
        PageVo<SiegeListVo> pageVo = new PageVo<>();
        if (count > 0) {
            List<SiegeElastic> collect = search.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
            log.info("siege elastic search2 -> {}", JsonUtils.toJson(collect));
            SiegeListVo vo;
            for (SiegeElastic siegeElastic : collect) {
                vo = new SiegeListVo();
                vo.setImages(siegeElastic.getSiege_images());
                vo.setSiegeTitle(siegeElastic.getSiege_title());
                vo.setUpNum(siegeElastic.getUp_num());
                vo.setSiegeId(siegeElastic.get_id());
                vo.setUserId(siegeElastic.getUser_id());
                vo.setCreateTime(DateUtils.parseString(DateUtils.utcToDate(siegeElastic.getCreate_time())));
                vos.add(vo);
            }
        }
        pageVo.setDtos(vos);
        pageVo.setCount(count);
        return pageVo;
    }
}
