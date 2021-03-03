package com.baiye.redscarf.common.exception;


import com.baiye.redscarf.common.enums.EnumMsg;
import com.baiye.redscarf.common.enums.ResultCodeEnum;

/**
 * @author <a href="jiangliuer_shawn@outlook.com">zhangyingdong</a>
 * @Date 2020/5/15 上午11:40
 */
public class BizException extends BaseException {

    /**
     * 无参构造
     */
    public BizException() {
        super();
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    /**
     * 异常信息构造
     *
     * @param message 异常信息
     */
    public BizException(String message) {
        super(-1, message);
    }

    /**
     * 异常状态和信息构造
     *
     * @param status  异常状态
     * @param message 异常信息
     */
    public BizException(Integer status, String message) {
        super(status, message);
    }

    /**
     * 异常信息和错误构造
     *
     * @param message 异常信息
     * @param cause   错误
     */
    public BizException(String message, Throwable cause) {
        super(-1, message, cause);
    }

    /**
     * 异常状态,信息及错误构造
     *
     * @param status    状态
     * @param message   信息
     * @param cause     错误
     */
    public BizException(Integer status, String message, Throwable cause) {
        super(status, message, cause);
    }

    /**
     * 枚举信息构造
     *
     * @param enumMsg 枚举信息
     */
    public BizException(EnumMsg<Integer> enumMsg) {
        super(enumMsg.getCode(), enumMsg.getMsg());
    }

    /**
     * 枚举信息和错误构造
     *
     * @param enumMsg 枚举信息
     * @param cause   错误
     */
    public BizException(EnumMsg<Integer> enumMsg, Throwable cause) {
        super(enumMsg.getCode(), enumMsg.getMsg(), cause);
    }

    /**
     *
     * @param resultCodeEnum 异常枚举
     */
    public BizException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getCode(), resultCodeEnum.getMsg());
    }

}
