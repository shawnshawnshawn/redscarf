package com.baiye.redscarf.user.rpc.gateway;

import com.baiye.redscarf.common.result.Result;
import com.baiye.redscarf.user.common.dto.UserAccountDTO;
import com.baiye.redscarf.user.common.vo.UserDataVo;
import com.baiye.redscarf.user.service.rpcService.IUserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author baiye
 * @date 2021/3/3 11:37 上午
 **/
@RestController
@RequestMapping("/user")
public class UserAccountController {

    private static final Logger log = LoggerFactory.getLogger(UserAccountController.class);

    @Resource
    private IUserAccountService iUserAccountService;

    @RequestMapping(value = "/getUserAccount/{id}", method = RequestMethod.GET)
    public Result<UserAccountDTO> get(@PathVariable("id") Long id) {
        log.info("ip -> {}, param -> {}", "", id);
        return Result.toResult(iUserAccountService.getUserAccountById(id));
    }

    @RequestMapping(value = "/getUserData/{id}", method = RequestMethod.GET)
    public Result<UserDataVo> getUserData(@PathVariable("id") Long id) {
        return Result.toResult(iUserAccountService.getUserData(id));
    }

}
