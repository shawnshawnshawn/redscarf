package com.baiye.redscarf.user.rpc.remote.impl;

import com.baiye.redscarf.user.common.dto.UserAccountDTO;
import com.baiye.redscarf.user.dao.entity.UserAccountEntity;
import com.baiye.redscarf.user.service.UserAccountService;
import com.baiye.redscarf.user.rpc.remote.IUserAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author baiye
 * @date 2021/3/3 10:26 上午
 **/
@Service
public class IUserAccountServiceImpl implements IUserAccountService {

    @Resource
    private UserAccountService userAccountService;

    @Override
    public UserAccountDTO getUserAccountById(Long userAccountId) {
        UserAccountEntity userAccountById = userAccountService.getUserAccountById(userAccountId);
        UserAccountDTO dto = new UserAccountDTO();
        BeanUtils.copyProperties(userAccountById, dto);
        return dto;
    }
}
