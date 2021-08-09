package com.baiye.redscarf.cache.cacheJ;


import com.baiye.redscarf.cache.redis.CacheService;
import com.baiye.redscarf.common.anno.CacheClear;
import com.baiye.redscarf.common.anno.CacheValue;
import com.baiye.redscarf.common.enums.ResultCodeEnum;
import com.baiye.redscarf.common.result.Result;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 缓存方法拦截处理器
 * @author 白也
 * @date 2020/12/3 下午6:14
 */
@Order(3)
@Component
public class CacheJMethodInterceptor implements MethodInterceptor, Serializable {

    private static final Logger log = LoggerFactory.getLogger(CacheJMethodInterceptor.class);

    @Resource
    private CacheService cacheService;

    @Override
    @SuppressWarnings("unchecked")
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Method method = methodInvocation.getMethod();
        if (method.isAnnotationPresent(CacheValue.class)) {
            CacheValue annotation = method.getAnnotation(CacheValue.class);

            //缓存的key
            String key = annotation.key();

            //缓存过期时间
            long expire = annotation.expire();

            //缓存时间单位
            TimeUnit timeUnit = annotation.timeUnit();

            // 缓存名称
            String name = annotation.name();

            //如果key为空,默认使用方法名作为key
            if (StringUtils.isBlank(key)) {
                throw Result.toBizException(ResultCodeEnum.CACHE_KEY_NULL);
            }

            //获取spel表达式的缓存key
            String realKey = CacheJSpelExpressionParser.parseCacheKey(key, method, methodInvocation.getArguments());

            //获取数据缓存
            Class<?> returnType = method.getReturnType();

            Object obj;
            if (List.class == returnType) {
                Type genericReturnType = method.getGenericReturnType();
                if (genericReturnType instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) genericReturnType;
                    String typeName = parameterizedType.getActualTypeArguments()[0].getTypeName();
                    obj = cacheService.getList(realKey, Class.forName(typeName));
                } else {
                    log.warn("返回集合类型数据，缓存未处理！");
                    return methodInvocation.proceed();
                }
            } else {
                obj = cacheService.get(realKey, returnType);
            }

            //缓存为空,执行方法并缓存方法执行结果
            if (obj == null) {
                Object invoke;
                try {
                    invoke  = method.invoke(methodInvocation.getThis(), methodInvocation.getArguments());
                } catch (InvocationTargetException e) {
                    throw e.getCause();
                }
                if (Objects.isNull(invoke)) {
                    return methodInvocation.proceed();
                }
                cacheService.set(realKey, invoke, expire, timeUnit);
                return invoke;
            } else {
                log.info("Walk the cache！ | execute -> {}", name);
                return obj;
            }
        } else if (method.isAnnotationPresent(CacheClear.class)) {
            CacheClear cacheClear = method.getAnnotation(CacheClear.class);
            String key = cacheClear.key();
            String name = cacheClear.name();
            log.info("delete cache -> {}", name);
            if (StringUtils.isNotBlank(key)) {
                String realKey = CacheJSpelExpressionParser.parseCacheKey(key, method, methodInvocation.getArguments());
                cacheService.delete(realKey);
            }

            String keyPrefix = cacheClear.keyPrefix();
            if (StringUtils.isNotBlank(keyPrefix)) {
                cacheService.deletePrefix(keyPrefix);
            }
        }
        //正常方法执行
        return methodInvocation.proceed();
    }
}
