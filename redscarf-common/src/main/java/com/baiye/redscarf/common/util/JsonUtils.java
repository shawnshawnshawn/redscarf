package com.baiye.redscarf.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baiye.redscarf.common.enums.ResultCodeEnum;
import com.baiye.redscarf.common.result.Result;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 请不要随意修改，如要添加元素，请创建新的类继承该类，在新的类中添加
 *
 * @author 白也
 * @date 2020/12/3 2:46 下午
 */
public class JsonUtils {

    private final static Logger log = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> String toJson(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return JSON.toJSONString(obj);
        } catch (Exception e) {
            log.error("obj to json failure", e);
            throw Result.toBizException(ResultCodeEnum.OBJ_TO_JSON_ERROR);
        }
    }

    public static <T> T toObj(String json, Class<T> tClass) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            return JSONObject.parseObject(json, tClass);
        } catch (Exception e) {
            log.error("json to obj failure", e);
            throw Result.toBizException(ResultCodeEnum.JSON_TO_OBJ_ERROR);
        }
    }

    public static <T> List<T> toList(String jsonData, Class<T> beanType) {
        if (StringUtils.isEmpty(jsonData)) {
            return null;
        }
        try {
            return JSONArray.parseArray(jsonData, beanType);
        } catch (Exception e) {
            log.error("json to list failure", e);
            throw Result.toBizException(ResultCodeEnum.JSON_TO_LIST_ERROR);
        }
    }

    public static <T> String toJson2(T obj) {
        if (obj == null) {
            return null;
        }

        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("obj to json failure", e);
            throw new RuntimeException(e);
        }
    }

    public static <T> T toObj2(String json, Class<T> type) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }

        try {
            return OBJECT_MAPPER.readValue(json, type);
        } catch (Exception e) {
            log.error("json to obj failure", e);
            throw new RuntimeException(e);
        }
    }

    public static <T> T toObj2(String json, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }

        try {
            return OBJECT_MAPPER.readValue(json, typeReference);
        } catch (Exception e) {
            log.error("json to obj failure", e);
            throw new RuntimeException(e);
        }
    }
}
