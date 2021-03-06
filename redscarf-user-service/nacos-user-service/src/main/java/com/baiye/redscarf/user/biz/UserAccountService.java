package com.baiye.redscarf.user.biz;

import com.baiye.redscarf.user.entity.UserAccountEntity;

/**
 * @author baiye
 * @date 2021/3/3 10:28 上午
 **/
public interface UserAccountService {

    UserAccountEntity getUserAccountById(Long userAccountId);

    UserAccountEntity getUserAccountByPhoneNoAndPassword(String phoneNo, String encoderByMd5);
}
