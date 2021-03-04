package com.baiye.redscarf.user.dao.mapper;

import com.baiye.redscarf.user.dao.entity.UserSiegeEntity;

public interface UserSiegeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserSiegeEntity record);

    int insertSelective(UserSiegeEntity record);

    UserSiegeEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserSiegeEntity record);

    int updateByPrimaryKey(UserSiegeEntity record);
}