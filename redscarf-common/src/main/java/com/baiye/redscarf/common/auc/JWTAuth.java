package com.baiye.redscarf.common.auc;

import com.baiye.redscarf.common.enums.ResultCodeEnum;
import com.baiye.redscarf.common.model.base.AuthID;
import com.baiye.redscarf.common.result.Result;
import com.baiye.redscarf.common.util.AopUtils;
import com.baiye.redscarf.common.util.HttpContextUtils;
import com.baiye.redscarf.common.util.RSAUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author baiye
 * @since 2021/8/10 11:00 上午
 **/
@Component
@Aspect
@Order(1)
public class JWTAuth {

    @Pointcut("@annotation(com.baiye.redscarf.common.anno.ApiAuth)")
    public void pointcut(){}

    @Around("pointcut()")
    public Object proceed(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest httpServletRequest = HttpContextUtils.getHttpServletRequest();
        if (httpServletRequest == null) {
            throw Result.toBizException(ResultCodeEnum.NO_TOKEN_INFO);
        }
        String authorization = httpServletRequest.getHeader("Authorization");
        if (StringUtils.isBlank(authorization)) {
            throw Result.toBizException(ResultCodeEnum.LOGIN_INVALID);
        }
        Claims claims = RSAUtils.verifyJwt(authorization);
        String id = claims.getId();
        AuthID authId = AopUtils.getAuthId(point);
        authId.setId(Long.valueOf(id));
        return point.proceed();
    }
}
