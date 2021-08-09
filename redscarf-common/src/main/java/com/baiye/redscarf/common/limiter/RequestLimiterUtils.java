package com.baiye.redscarf.common.limiter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author baiye
 * @since 2021/5/20 3:43 下午
 **/
@Component
public abstract class RequestLimiterUtils implements BeanPostProcessor {

    private static final Logger log = LoggerFactory.getLogger(RequestLimiterUtils.class);

    private static final Map<String, RequestLimiter> requestLimiterMap = new ConcurrentHashMap<>();

    public static RequestLimiter getRequestLimiter(String method, int poolSize) {
        synchronized (method.intern()) {
            RequestLimiter requestLimiter = requestLimiterMap.get(method);
            if (requestLimiter == null) {
                requestLimiter = RequestLimiter.create(method, poolSize);
                requestLimiterMap.putIfAbsent(method, requestLimiter);
            }
            return requestLimiter;
        }
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        for (Method method : methods) {
            RequestLimit requestLimit = AnnotationUtils.findAnnotation(method, RequestLimit.class);
            if (null != requestLimit) {
                if (requestLimit.limitEnum().equals(RequestLimitEnum.METHOD)) {
                    RequestLimiter requestLimiter = getRequestLimiter(method.getName(), requestLimit.concurrent());
                    requestLimiterMap.putIfAbsent(method.getName(), requestLimiter);
                }
            }
        }
        return bean;
    }

    /**
     * 获取请求池本地缓存数据量
     * @return size
     */
    public int getRequestLimiterMapSize() {
        return requestLimiterMap.size();
    }

    /**
     * 获取请求池本地缓存map
     * @return map
     */
    public Map<String, RequestLimiter> getRequestLimiterMap() {
        return requestLimiterMap;
    }
}
