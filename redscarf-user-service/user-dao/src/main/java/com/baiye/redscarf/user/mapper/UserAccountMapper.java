package com.baiye.redscarf.user.mapper;

import com.baiye.redscarf.user.entity.UserAccountEntity;
import org.apache.ibatis.annotations.Param;

public interface UserAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserAccountEntity record);

    int insertSelective(UserAccountEntity record);

    UserAccountEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserAccountEntity record);

    int updateByPrimaryKey(UserAccountEntity record);

    UserAccountEntity selectByPhoneNoAndPassword(@Param("phoneNo") String phoneNo, @Param("password") String encoderByMd5);

}