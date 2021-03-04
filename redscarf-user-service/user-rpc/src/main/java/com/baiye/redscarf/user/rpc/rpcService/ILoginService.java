package com.baiye.redscarf.user.rpc.rpcService;

import com.baiye.redscarf.user.common.form.LoginForm;
import com.baiye.redscarf.user.common.vo.LoginVo;

/**
 * @author baiye
 * @date 2021/3/4 3:45 下午
 **/
public interface ILoginService {
    LoginVo login(LoginForm form);
}
