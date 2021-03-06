package com.baiye.redscarf.siege.mapper;

import com.baiye.redscarf.siege.entity.SiegePostsEntity;
import com.baiye.redscarf.siege.obj.PageQuerySiegeConditions;

import java.util.List;

public interface SiegePostsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SiegePostsEntity record);

    int insertSelective(SiegePostsEntity record);

    SiegePostsEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SiegePostsEntity record);

    int updateByPrimaryKey(SiegePostsEntity record);

    List<SiegePostsEntity> pageQuerySiegeByConditions(PageQuerySiegeConditions obj);

    Integer countSiegeByConditions(PageQuerySiegeConditions obj);
}