package com.baiye.redscarf.user.service.impl;

import com.baiye.redscarf.common.enums.ResultCodeEnum;
import com.baiye.redscarf.common.result.Result;
import com.baiye.redscarf.user.dao.entity.UserAccountEntity;
import com.baiye.redscarf.user.dao.mapper.UserAccountMapper;
import com.baiye.redscarf.user.service.UserAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author baiye
 * @date 2021/3/3 10:28 上午
 **/
@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Resource
    private UserAccountMapper userAccountMapper;

    @Override
    public UserAccountEntity getUserAccountById(Long userAccountId) {
        UserAccountEntity uae = userAccountMapper.selectByPrimaryKey(userAccountId);
        if (uae == null) {
            throw Result.toBizException(ResultCodeEnum.USER_NOT_EXIST);
        }
        return uae;
    }

    @Override
    public UserAccountEntity getUserAccountByPhoneNoAndPassword(String phoneNo, String encoderByMd5) {
        UserAccountEntity uae = userAccountMapper.selectByPhoneNoAndPassword(phoneNo, encoderByMd5);
        if (uae == null ) {
            throw Result.toBizException(ResultCodeEnum.LOGIN_NO_OR_PWD_ERROR);
        }
        return uae;
    }
}
