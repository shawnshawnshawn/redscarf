package com.baiye.redscarf.siege.mapper;

import com.baiye.redscarf.siege.entity.SiegeDataEntity;
import org.apache.ibatis.annotations.Param;

public interface SiegeDataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SiegeDataEntity record);

    int insertSelective(SiegeDataEntity record);

    SiegeDataEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SiegeDataEntity record);

    int updateByPrimaryKey(SiegeDataEntity record);

    SiegeDataEntity selectBySiegeId(@Param("siegeId") Long id);
}