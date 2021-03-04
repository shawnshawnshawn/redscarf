package com.baiye.redscarf.cache.redis.impl;

import com.baiye.redscarf.cache.redis.CacheService;
import com.baiye.redscarf.common.util.JsonUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 请不要随意修改，如要添加元素，请创建新的类继承该类，在新的类中添加
 *
 * @author 白也
 * @date 2020/12/3 2:54 下午
 */
@Component
public class CacheServiceImpl implements CacheService {

    @Resource
    protected StringRedisTemplate valueOps;

    @Resource
    protected RedisTemplate<String, String> redisTemplate;

    @Override
    public <V> V  get(String key, Class<V> valueType) {
        return JsonUtils.toObj(valueOps.opsForValue().get(key), valueType);
    }

    @Override
    public <T> List<T> getList(String key, Class<T> valueType) {
        return JsonUtils.toList(valueOps.opsForValue().get(key), valueType);
    }

    @Override
    public <V> void set(String key, V value) {
        valueOps.opsForValue().set(key, JsonUtils.toJson(value));
    }

    @Override
    public <V> void set(String key, V value, long time, TimeUnit timeUnit) {
        valueOps.opsForValue().set(key, JsonUtils.toJson(value), time, timeUnit);
    }

    @Override
    public Boolean hasKey(String key) {
        return valueOps.hasKey(key);
    }

    @Override
    public void delete(String... keys) {
        valueOps.opsForValue().getOperations().delete(Arrays.asList(keys));
    }

    @Override
    public void deletePrefix(String keyPrefix) {
        Set<String> keys = keys(keyPrefix);
        keys.forEach(this::delete);
    }

    @Override
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern + "*");
    }

    @Override
    public void incr(String key) {
        valueOps.opsForValue().increment(key);
    }
}
