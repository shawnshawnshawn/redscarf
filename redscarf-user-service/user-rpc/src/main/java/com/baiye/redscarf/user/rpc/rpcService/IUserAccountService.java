package com.baiye.redscarf.user.rpc.rpcService;

import com.baiye.redscarf.user.common.dto.UserAccountDTO;

/**
 * @author baiye
 * @date 2021/3/3 10:26 上午
 **/
public interface IUserAccountService {

    UserAccountDTO getUserAccountById(Long userAccountId);
}
