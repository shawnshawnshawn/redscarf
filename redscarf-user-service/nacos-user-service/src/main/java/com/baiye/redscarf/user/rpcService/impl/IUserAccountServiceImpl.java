package com.baiye.redscarf.user.rpcService.impl;

import com.baiye.redscarf.user.biz.UserAccountService;
import com.baiye.redscarf.user.biz.UserSiegeService;
import com.baiye.redscarf.user.dto.UserAccountDTO;
import com.baiye.redscarf.user.vo.UserDataVo;
import com.baiye.redscarf.user.entity.UserAccountEntity;
import com.baiye.redscarf.user.rpcService.IUserAccountService;
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

    @Resource
    private UserSiegeService userSiegeService;

    @Override
    public UserAccountDTO getUserAccountById(Long userAccountId) {
        UserAccountEntity userAccountById = userAccountService.getUserAccountById(userAccountId);
        UserAccountDTO dto = new UserAccountDTO();
        BeanUtils.copyProperties(userAccountById, dto);
        return dto;
    }

    @Override
    public UserDataVo getUserData(Long id) {
        UserDataVo vo = new UserDataVo();
        Integer publishNum = userSiegeService.countUserCollectNum(id);
        vo.setCollectNum(publishNum);
        return vo;
    }
}
