package com.baiye.redscarf.common.result;


import com.baiye.redscarf.common.enums.ResultCodeEnum;
import com.baiye.redscarf.common.exception.BizException;
import lombok.Data;

import java.util.List;

/**
 * 请不要随意修改，如要添加元素，请创建新的类继承该类，在新的类中添加
 * 返回信息视图
 * @author 白也
 * @date 2020/11/17 2:20 下午
 */
@Data
public class Result<T> {

    private boolean success = true;

    private int code;

    private String message;

    private T data;

    private long t = System.currentTimeMillis();

    public void set(ResultCodeEnum codeEnum){
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMsg();
    }

    public static <T> Result<T> ofSuccess(){
        Result<T> result = new Result<T>();
        result.set(ResultCodeEnum.SUCCESS);
        return result;
    }

    public static <T> Result<T> ofFail() {
        Result<T> result = new Result<T>();
        result.set(ResultCodeEnum.FAIL);
        result.setSuccess(false);
        return result;
    }

    public static <T> Result<T> ofFail(Integer code, String message) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setMessage(message);
        result.setSuccess(false);
        return result;
    }

    public static <T> Result<T> ofFail(ResultCodeEnum resultCodeEnum) {
        Result<T> result = new Result<T>();
        result.set(resultCodeEnum);
        result.setSuccess(false);
        return result;
    }

    public static <T> Result<T> ofError(ResultCodeEnum resultCodeEnum) {
        Result<T> result = new Result<T>();
        result.set(resultCodeEnum);
        result.setSuccess(false);
        return result;
    }

    public static BizException toBizException(ResultCodeEnum resultCodeEnum) {
        return new BizException(resultCodeEnum);
    }

    public static BizException toBizException(Throwable cause) {
        return new BizException(cause);
    }

    public static BizException toBizException(int code, String message) {
        return new BizException(code, message);
    }

    public static BizException toBizException(ResultCodeEnum resultCodeEnum, Object... args) {
        return new BizException(resultCodeEnum.getCode(), resultCodeEnum.getMsg(args));
    }

    public static <T> Result<T> toResult(T data) {
        Result<T> result = new Result<T>();
        result.set(ResultCodeEnum.SUCCESS);
        result.setData(data);
        return result;
    }

    public static <T> Result<List<T>> toResult(List<T> data) {
        Result<List<T>> result = new Result<List<T>>();
        result.set(ResultCodeEnum.SUCCESS);
        result.setData(data);
        return result;
    }
}
