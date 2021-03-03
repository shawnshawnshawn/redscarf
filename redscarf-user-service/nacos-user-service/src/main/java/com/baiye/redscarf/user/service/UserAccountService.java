package com.baiye.redscarf.user.service;

import com.baiye.redscarf.user.dao.entity.UserAccountEntity;

/**
 * @author baiye
 * @date 2021/3/3 10:28 上午
 **/
public interface UserAccountService {

    UserAccountEntity getUserAccountById(Long userAccountId);
}
