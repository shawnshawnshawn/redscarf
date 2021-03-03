package com.baiye.redscarf.common.enums;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;

/**
 * @author <a href="jiangliuer_shawn@outlook.com">zhangyingdong</a>
 * @Date 2020/5/15 上午11:36
 */
public interface EnumMsg<T> extends EnumCode<T> {

    String getMsg();

    /**
     * 格式化枚举描述信息.
     *
     * @param formatArgs 格式化参数
     * @return 枚举描述信息
     */
    default String getMsg(Object... formatArgs) {
        String msg = getMsg();
        if (ArrayUtils.isEmpty(formatArgs) || StringUtils.isBlank(msg)) {
            return msg;
        }

        return MessageFormat.format(msg, formatArgs);
    }
}
