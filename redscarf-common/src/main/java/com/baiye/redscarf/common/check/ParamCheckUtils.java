package com.baiye.redscarf.common.check;

import com.cheapp.work2.common.enums.ResultCodeEnum;
import com.cheapp.work2.common.result.Result;
import com.cheapp.work2.common.util.DateUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author baiye
 * @since 2021/7/2 10:14 上午
 **/
public class ParamCheckUtils {

    public static void checkParam(Object[] args) throws IllegalAccessException {
        if (ArrayUtils.isEmpty(args)) {
            return;
        }
        for (Object arg : args) {
            if (Objects.isNull(arg)) {
                continue;
            }
            Class<?> aClass = arg.getClass();
            doCheckParam(arg, aClass);
        }
    }

    private static void doCheckParam(Object arg, Class<?> aClass) throws IllegalAccessException {
        if (Objects.equals(aClass, Object.class)) {
            return;
        }

        Field[] declaredFields = aClass.getDeclaredFields();
        if (declaredFields.length == 0) {
            return;
        }

        for (Field field : declaredFields) {
            CheckParam checkParam = field.getAnnotation(CheckParam.class);
            if (checkParam == null) {
                continue;
            }
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            Object value = field.get(arg);
            String name = checkParam.name();
            if (StringUtils.isBlank(name)) {
                throw Result.toBizException(ResultCodeEnum.PARAM_NAME_NOT_NULL);
            }

            // 对象校验
            if (checkParam.notNull()) {
                if (Objects.isNull(value)) {
                    throw Result.toBizException(ResultCodeEnum.PARAM_NULL_WITH, name);
                }
            }

            // 字符串校验
            if (checkParam.notBlank()) {
                if (Objects.isNull(value) || StringUtils.isBlank(value.toString())) {
                    throw Result.toBizException(ResultCodeEnum.PARAM_NULL_WITH, name);
                }
            }

            // 是否大于校验
            if (checkParam.ge() != -1) {
                if (!Objects.isNull(value) && Integer.parseInt(value.toString()) < checkParam.ge()) {
                    throw Result.toBizException(ResultCodeEnum.CHECK_PARAM_ASSIGN_ERROR, name, "大于等于" + checkParam.ge());
                }
            }

            // 是否小于校验
            if (checkParam.le() != -1) {
                if (!Objects.isNull(value) && Integer.parseInt(value.toString()) > checkParam.le()) {
                    throw Result.toBizException(ResultCodeEnum.CHECK_PARAM_ASSIGN_ERROR, name, "小于等于" + checkParam.le());
                }
            }

            // 长度校验
            if (checkParam.length() != 0) {
                if (!Objects.isNull(value) && value.toString().length() > checkParam.length()) {
                    throw Result.toBizException(ResultCodeEnum.CHECK_PARAM_LENGTH_ERROR, name, checkParam.length());
                }
            }

            if (checkParam.regularEnum() != RegularEnum.NON) {
                String pattern = checkParam.regularEnum().getRegular();
                if (StringUtils.isNotBlank(checkParam.regular())) {
                    pattern = checkParam.regular();
                }
                if (StringUtils.isBlank(pattern)) {
                    throw Result.toBizException(ResultCodeEnum.PARAM_PATTERN_ERROR, name);
                }
                boolean matches = Pattern.matches(pattern, value.toString());
                if (!matches) {
                    throw Result.toBizException(ResultCodeEnum.PARAM_PATTERN_ERROR, name);
                }
            }

            //in校验
            checkIn(checkParam, value, name);

            // 日期格式校验
            if (!Objects.isNull(value)) {
                if (StringUtils.isNotBlank(checkParam.dateFormatChange())) {
                    if (checkParam.dateFormatChange().equals("utc")) {
                        value = DateUtils.utcToDateStr((String) value);
                        field.set(arg, value);
                    }
                }
            }
        }
        doCheckParam(arg, aClass.getSuperclass());
    }

    public static void checkIn(CheckParam checkParam, Object value, String name) {
        if (StringUtils.isNotBlank(checkParam.in())) {
            if (value == null) {
                throw Result.toBizException(ResultCodeEnum.PARAM_NULL_WITH, name);
            }

            String[] split = checkParam.in().split(",");
            if (split.length == 0) {
                throw Result.toBizException(ResultCodeEnum.PARAM_FORMAT_ERROR);
            }

            boolean allow = false;
            for (String allowValue :
                    split) {
                if (StringUtils.isBlank(allowValue)) {
                    throw Result.toBizException(ResultCodeEnum.PARAM_FORMAT_ERROR);
                }
                if (allowValue.equals(value.toString())) {
                    allow = true;
                    break;
                }
            }

            if (!allow) {
                throw Result.toBizException(ResultCodeEnum.CHECK_PARAM_ERROR, name, split);
            }
        }
    }

    public static void main(String[] args) {
        String pa = RegularEnum.PHONE.getRegular();
        boolean matches = Pattern.matches(pa, "18625506701");
        System.out.println(matches);
    }
}
