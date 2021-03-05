package com.baiye.redscarf.siege.common.enums;

import com.baiye.redscarf.common.enums.EnumMsg;
import com.baiye.redscarf.common.enums.ResultCodeEnum;
import com.baiye.redscarf.common.result.Result;

/**
 * @author baiye
 * @date 2021/3/4 5:53 下午
 **/
public enum SiegeTypeEnum implements EnumMsg<Integer> {

    EAST(1, "east"),

    SOUTH(2, "south"),

    WEST(3, "west"),

    NORTH(4, "north"),
    ;

    private final Integer code;

    private final String msg;

    SiegeTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public static SiegeTypeEnum getSiegeTypeByMsg(String msg) {
        for (SiegeTypeEnum value : SiegeTypeEnum.values()) {
            if (value.getMsg().endsWith(msg)) {
                return value;
            }
        }
        throw Result.toBizException(ResultCodeEnum.SIEGE_TYPE_NOT_EXIST);
    }
}
