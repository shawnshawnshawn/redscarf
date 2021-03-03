package com.baiye.redscarf.user.service.impl;

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
            throw new RuntimeException("用户账户不存在");
        }
        return uae;
    }
}
