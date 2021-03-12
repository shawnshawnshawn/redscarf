package com.baiye.redscarf.common.util;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;

/**
 * O/X映射工具类.
 *
 * @author 白也
 * @date 2021/1/19 5:25 下午
 */
public class OXMUtils {

    private static final Jaxb2Mapper MAPPER = new Jaxb2Mapper();


    /**
     * 将对象转换为XML格式的字符串(使用默认编码).
     *
     * @param instance
     * @param <T>
     * @return
     */
    public static <T> String marshal(T instance) {
        return marshal(instance, null);
    }

    /**
     * 将对象转换为XML格式的字符串.
     *
     * @param instance
     * @param charset
     * @param <T>
     * @return
     */
    public static <T> String marshal(T instance, Charset charset) {
        if (instance == null) {
            return null;
        }

        return MAPPER.marshal(instance, charset);
    }


    /**
     * 将XML格式的字符串转换为对象.
     *
     * @param xml
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T unmarshal(String xml, Class<T> clazz) {
        if (StringUtils.isBlank(xml) || clazz == null) {
            return null;
        }

        return MAPPER.unmarshal(xml, clazz);
    }
}
