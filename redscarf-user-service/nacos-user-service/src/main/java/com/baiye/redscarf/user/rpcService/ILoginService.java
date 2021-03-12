package com.baiye.redscarf.user.rpcService;

import com.baiye.redscarf.common.param.BaseForm;
import com.baiye.redscarf.user.form.LoginForm;
import com.baiye.redscarf.user.vo.LoginVo;

/**
 * @author baiye
 * @date 2021/3/4 3:45 下午
 **/
public interface ILoginService {
    LoginVo login(LoginForm form);

    void loginOut(BaseForm form);
}
