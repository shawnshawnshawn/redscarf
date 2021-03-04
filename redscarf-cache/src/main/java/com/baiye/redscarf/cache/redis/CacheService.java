package com.baiye.redscarf.cache.redis;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 请不要随意修改，如要添加元素，请创建新的类继承该类，在新的类中添加
 *
 * @author 白也
 * @date 2020/12/3 2:54 下午
 */
public interface CacheService {

    /**
     * 获取缓存内容
     * @param key key
     * @param valueType clazz
     * @return 内容
     */
    <V> V get(String key, Class<V> valueType);


    /**
     * 获取缓存列表
     * @param key   key
     * @param valueType clazz
     * @param <T> 泛型
     * @return 列表
     */
    <T> List<T> getList(String key, Class<T> valueType);

    /**
     * 缓存数据
     * @param key   key
     * @param value value
     */
    <V> void set(String key, V value);

    /**
     * 缓存数据
     * @param key   key
     * @param value value
     * @param time  时间
     * @param timeUnit 单位
     */
    <V> void set(String key, V value, long time, TimeUnit timeUnit);

    /**
     * 是否存在key
     * @param key key
     * @return true of false
     */
    Boolean hasKey(String key);

    /**
     * 删除key
     * @param keys key
     */
    void delete(String... keys);

    /**
     * 删除前缀为keyPrefix的key
     * @param keyPrefix key
     */
    void deletePrefix(String keyPrefix);

    /**
     * 模糊查询
     * @param pattern key
     * @return key 集合
     */
    Set<String> keys(String pattern);

    /**
     * 自增计数
     * @param key key
     */
    void incr(String key);
}
