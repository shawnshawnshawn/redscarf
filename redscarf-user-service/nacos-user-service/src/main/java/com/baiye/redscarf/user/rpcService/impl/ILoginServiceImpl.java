package com.baiye.redscarf.user.rpcService.impl;

import com.baiye.redscarf.cache.redis.CacheService;
import com.baiye.redscarf.common.param.BaseForm;
import com.baiye.redscarf.common.util.MD5Utils;
import com.baiye.redscarf.common.util.RandomKeyUtils;
import com.baiye.redscarf.user.biz.UserAccountService;
import com.baiye.redscarf.user.constant.CacheConstants;
import com.baiye.redscarf.user.form.LoginForm;
import com.baiye.redscarf.user.vo.LoginVo;
import com.baiye.redscarf.user.entity.UserAccountEntity;
import com.baiye.redscarf.user.rpcService.ILoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author baiye
 * @date 2021/3/4 3:45 下午
 **/
@Service
public class ILoginServiceImpl implements ILoginService {

    private static final Logger log = LoggerFactory.getLogger(ILoginServiceImpl.class);

    @Resource
    private UserAccountService userAccountService;

    @Resource
    private CacheService cacheService;

    @Override
    public LoginVo login(LoginForm form) {
        log.info("login param -> {}", form.toString());
        UserAccountEntity uae = userAccountService.getUserAccountByPhoneNoAndPassword(form.getPhoneNo(), MD5Utils.encoderByMd5(form.getPassword()));
        String token = RandomKeyUtils.getUUID();
        String key = CacheConstants.USER_LOGIN_PREFIX + token;
        cacheService.set(key, uae, 7, TimeUnit.DAYS);
        return new LoginVo(token);
    }

    @Override
    public void loginOut(BaseForm form) {
        String key = CacheConstants.USER_LOGIN_PREFIX + form.getToken();
        cacheService.delete(key);
    }
}
