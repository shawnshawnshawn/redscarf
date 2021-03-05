package com.baiye.redscarf.user.service.rpcService;

import com.baiye.redscarf.user.common.dto.UserAccountDTO;
import com.baiye.redscarf.user.common.vo.UserDataVo;

/**
 * @author baiye
 * @date 2021/3/3 10:26 上午
 **/
public interface IUserAccountService {

    UserAccountDTO getUserAccountById(Long userAccountId);

    UserDataVo getUserData(Long id);
}
