package com.baiye.redscarf.common.util;

import com.alibaba.fastjson.JSONObject;
import com.baiye.redscarf.common.result.Result;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 请不要随意修改，如要添加元素，请创建新的类继承该类，在新的类中添加
 *
 * @author 白也
 * @date 2020/11/17 2:41 下午
 */
public class DataConverterUtils {

    public static <T> T convertObject(Object source, Class<T> targetType) {
        T t = BeanUtils.instantiateClass(targetType);
        if (source == null) {
            return t;
        }
        BeanUtils.copyProperties(source, t);
        return t;
    }

    public static <S,T> List<T> convertList(List<S> source, Class<T> targetType) {
        if (CollectionUtils.isEmpty(source)) {
            return Lists.newArrayList();
        }
        List<T> targetList = Lists.newArrayList();
        source.forEach(o -> {
            T t = BeanUtils.instantiateClass(targetType);
            BeanUtils.copyProperties(o,t);
            targetList.add(t);
        });
        return targetList;
    }

    public static <E> Result<E> convertResultObject(String source, Class<E> eClass) {
        Result result = JsonUtils.toObj2(source, Result.class);
        Result<E> res = Result.ofFail();
        if (source == null || result == null) {
            return res;
        }
        BeanUtils.copyProperties(result, res);
        JSONObject jsonObject = JSONObject.parseObject(source);
        E data = jsonObject.getObject("data", eClass);
        res.setData(data);
        return res;
    }
}
