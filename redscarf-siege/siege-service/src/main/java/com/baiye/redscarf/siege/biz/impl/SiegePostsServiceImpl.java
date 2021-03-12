package com.baiye.redscarf.siege.biz.impl;

import com.baiye.redscarf.common.result.PageVo;
import com.baiye.redscarf.siege.biz.SiegePostsService;
import com.baiye.redscarf.siege.entity.SiegePostsEntity;
import com.baiye.redscarf.siege.mapper.SiegePostsMapper;
import com.baiye.redscarf.siege.obj.PageQuerySiegeConditions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author baiye
 * @date 2021/3/4 5:06 下午
 **/
@Service
public class SiegePostsServiceImpl implements SiegePostsService {

    @Resource
    private SiegePostsMapper siegePostsMapper;

    @Override
    public PageVo<SiegePostsEntity> pageQuerySiegeByConditions(PageQuerySiegeConditions obj) {
        Integer count = siegePostsMapper.countSiegeByConditions(obj);
        List<SiegePostsEntity> siegePostsEntities = new ArrayList<>(obj.getPageSize());
        if (count > 0) {
            siegePostsEntities = siegePostsMapper.pageQuerySiegeByConditions(obj);
        }
        return new PageVo<>(count, siegePostsEntities);
    }
}
