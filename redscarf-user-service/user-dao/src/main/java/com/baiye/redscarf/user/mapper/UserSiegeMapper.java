package com.baiye.redscarf.user.mapper;

import com.baiye.redscarf.user.entity.UserSiegeEntity;
import org.apache.ibatis.annotations.Param;

public interface UserSiegeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserSiegeEntity record);

    int insertSelective(UserSiegeEntity record);

    UserSiegeEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserSiegeEntity record);

    int updateByPrimaryKey(UserSiegeEntity record);

    Integer countUserCollectNum(@Param("userId") Long id);
}