package com.baiye.redscarf.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author <a href="jiangliuer_shawn@outlook.com">zhangyingdong</a>
 * @Date 2020/5/15 上午11:39
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {

    private Integer status;

    public BaseException() {
        this(-1);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(Integer status) {
        this(status, null);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Integer status, String message) {
        super(message);
        this.status = status;
    }

    public BaseException(Integer status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }
}
