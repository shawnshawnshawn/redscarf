package com.baiye.redscarf.common.result;

import com.baiye.redscarf.common.enums.ResultCodeEnum;
import lombok.Data;

import java.util.List;

/**
 * @author baiye
 * @date 2021/3/4 5:33 下午
 **/
@Data
public class PageResult<T> {

    private boolean success = true;

    private int code;

    private String message;

    private PageVo<T> data;

    private long t = System.currentTimeMillis();

    public PageResult() {
    }

    public void set(ResultCodeEnum codeEnum){
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMsg();
    }

    public static <T> PageResult<T> toPageResult(PageVo<T> data) {
        PageResult<T> result = new PageResult<T>();
        result.set(ResultCodeEnum.SUCCESS);
        result.setData(data);
        return result;
    }

    public static <T> PageResult<T> toPageResult(int count, List<T> dtos) {
        PageResult<T> result = new PageResult<T>();
        result.set(ResultCodeEnum.SUCCESS);
        result.setData(new PageVo<>(count, dtos));
        return result;
    }

    public static <T> PageResult<T> ofSuccess(){
        PageResult<T> result = new PageResult<T>();
        result.set(ResultCodeEnum.SUCCESS);
        return result;
    }

    public static <T> PageResult<T> ofFail() {
        PageResult<T> result = new PageResult<T>();
        result.set(ResultCodeEnum.FAIL);
        result.setSuccess(false);
        return result;
    }

    public static <T> PageResult<T> ofFail(Integer code, String message) {
        PageResult<T> result = new PageResult<T>();
        result.setCode(code);
        result.setMessage(message);
        result.setSuccess(false);
        return result;
    }

    public static <T> PageResult<T> ofFail(ResultCodeEnum resultCodeEnum) {
        PageResult<T> result = new PageResult<T>();
        result.set(resultCodeEnum);
        result.setSuccess(false);
        return result;
    }
}
