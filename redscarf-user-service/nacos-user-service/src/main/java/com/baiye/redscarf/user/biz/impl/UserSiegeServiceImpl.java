package com.baiye.redscarf.user.biz.impl;

import com.baiye.redscarf.user.mapper.UserSiegeMapper;
import com.baiye.redscarf.user.biz.UserSiegeService;
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

    @Override
    public Integer countUserCollectNum(Long id) {
        Integer count = userSiegeMapper.countUserCollectNum(id);
        return count == null ? 0 : count;
    }
}
