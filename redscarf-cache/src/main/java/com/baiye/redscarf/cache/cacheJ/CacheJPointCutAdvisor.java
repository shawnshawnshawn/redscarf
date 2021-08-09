package com.baiye.redscarf.cache.cacheJ;

import com.baiye.redscarf.common.anno.CacheClear;
import com.baiye.redscarf.common.anno.CacheValue;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * 添加注解切面
 * @author 白也
 * @date 2020/12/3 下午5:44
 */

@Component
public class CacheJPointCutAdvisor extends AbstractPointcutAdvisor {

    @Resource
    private CacheJMethodInterceptor cacheJMethodInterceptor;

    private final StaticMethodMatcherPointcut staticMethodMatcherPointcut = new StaticMethodMatcherPointcut() {
        @Override
        public boolean matches(Method method, Class<?> aClass) {
            return method.isAnnotationPresent(CacheValue.class) || aClass.isAnnotationPresent(CacheValue.class)
                    || method.isAnnotationPresent(CacheClear.class) || aClass.isAnnotationPresent(CacheClear.class);
        }
    };

    @Override
    public Pointcut getPointcut() {
        return this.staticMethodMatcherPointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.cacheJMethodInterceptor;
    }
}
