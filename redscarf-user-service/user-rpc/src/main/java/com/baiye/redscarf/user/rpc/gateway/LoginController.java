package com.baiye.redscarf.user.rpc.gateway;

import com.baiye.redscarf.common.param.BaseForm;
import com.baiye.redscarf.common.result.Result;
import com.baiye.redscarf.user.common.form.LoginForm;
import com.baiye.redscarf.user.common.vo.LoginVo;
import com.baiye.redscarf.user.service.rpcService.ILoginService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author baiye
 * @date 2021/3/4 3:44 下午
 **/
@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private ILoginService iLoginService;

    @RequestMapping(value = "/up", method = RequestMethod.POST)
    public Result<LoginVo> login(@RequestBody LoginForm form) {
        return Result.toResult(iLoginService.login(form));
    }

    @RequestMapping(value = "/out", method = RequestMethod.POST)
    public Result<Object> out(@RequestBody BaseForm form) {
        iLoginService.loginOut(form);
        return Result.ofSuccess();
    }
}
