package com.baiye.redscarf.common.limiter;

import com.baiye.redscarf.common.elastic.Log;
import com.baiye.redscarf.common.properties.NacosDynamicProperties;
import com.baiye.redscarf.common.util.HttpContextUtils;
import lombok.Data;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author baiye
 * @since 2021/5/19 11:49 上午
 **/
@Aspect
@Component
@Order(1)
public class RequestLimitInterceptor {

    private static final Logger log = LoggerFactory.getLogger(RequestLimitInterceptor.class);

    @Resource
    private NacosDynamicProperties nacosDynamicProperties;

    @Pointcut("@annotation(com.baiye.redscarf.common.limiter.RequestLimit)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void pre(JoinPoint joinPoint) {
        if (!nacosDynamicProperties.isConcurrencyLimitOn()) {
            return;
        }
        RLimit rLimit = getLimitTypeStr(joinPoint);
        RequestLimiter requestLimiter = RequestLimiterUtils.getRequestLimiter(rLimit.getTypeStr(), rLimit.getConcurrent());
        Log.INFO.print(log, "RequestLimiter addr -> {}", System.identityHashCode(requestLimiter));
        Log.INFO.print(log, "请求前 - 请求池大小 -> {}", requestLimiter.getPoolSize());
        requestLimiter.consume();
        Log.INFO.print(log, "请求池大小 -> {}", requestLimiter.getPoolSize());
    }

    @After("pointcut()")
    public void after(JoinPoint joinPoint) {
        if (!nacosDynamicProperties.isConcurrencyLimitOn()) {
            return;
        }
        RLimit rLimit = getLimitTypeStr(joinPoint);
        RequestLimiter requestLimiter = RequestLimiterUtils.getRequestLimiter(rLimit.typeStr, rLimit.getConcurrent());
        Log.INFO.print(log, "RequestLimiter addr -> {}", System.identityHashCode(requestLimiter));
        requestLimiter.free();
        Log.INFO.print(log, "请求后 - 请求池大小 -> {}", requestLimiter.getPoolSize());
    }

    private RLimit getLimitTypeStr(JoinPoint joinPoint) {
        HttpServletRequest httpServletRequest = HttpContextUtils.getHttpServletRequest();
        Assert.notNull(httpServletRequest, "request must not be null");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RequestLimit annotation = method.getAnnotation(RequestLimit.class);
        RequestLimitEnum requestLimitEnum = annotation.limitEnum();
        String str = method.getName();
        switch (requestLimitEnum) {
            case IP:
                str = str + ":" + HttpContextUtils.getClientIp(httpServletRequest);
                break;
            case TOKEN:
                str = str + ":" + httpServletRequest.getHeader("Authorization");
                break;
        }
        return new RLimit(str, annotation.concurrent());
    }

    @Data
    static class RLimit {
        private String typeStr;

        private Integer concurrent;

        RLimit(String typeStr, Integer concurrent) {
            this.concurrent = concurrent;
            this.typeStr = typeStr;
        }
    }
}
