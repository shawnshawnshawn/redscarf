package com.baiye.redscarf.user.service.impl;

import com.baiye.redscarf.user.dao.mapper.UserSiegeMapper;
import com.baiye.redscarf.user.service.UserSiegeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author baiye
 * @date 2021/3/4 3:42 下午
 **/
@Service
public class UserSiegeServiceImpl implements UserSiegeService {

    @Resource
    private UserSiegeMapper userSiegeMapper;
}
