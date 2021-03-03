package com.baiye.redscarf.user.dao.mapper;

import com.baiye.redscarf.user.dao.entity.UserAccountEntity;

public interface UserAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserAccountEntity record);

    int insertSelective(UserAccountEntity record);

    UserAccountEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserAccountEntity record);

    int updateByPrimaryKey(UserAccountEntity record);
}