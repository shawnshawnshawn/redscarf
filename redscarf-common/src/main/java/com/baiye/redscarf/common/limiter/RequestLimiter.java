package com.baiye.redscarf.common.limiter;

import com.baiye.redscarf.common.enums.ResultCodeEnum;
import com.baiye.redscarf.common.result.Result;
import lombok.Data;

/**
 * 并发请求限制
 * 并发大小：poolSize
 * <p>
 * 创建并发请求池，每次请求消耗请求池，请求完成之后释放请求池
 * 通过{@link com.baiye.redscarf.common.limiter.RequestLimit}注解切面执行逻辑
 * 或通过调用create方法创建请求池，自定义处理限制逻辑
 * </p>
 *
 * @author baiye
 * @since 2021/5/19 2:07 下午
 **/
@Data
public class RequestLimiter {

    private static final String DEFAULT_METHOD_POOL = "default_method_group";

    private static final int DEFAULT_POOL_SIZE = 100;


    private String method;

    private int poolSize;

    public RequestLimiter() {
    }

    public RequestLimiter(String method, int poolSize) {
        this.method = method;
        this.poolSize = poolSize;
    }

    public static RequestLimiter create() {
        return create(DEFAULT_METHOD_POOL, DEFAULT_POOL_SIZE);
    }

    public static RequestLimiter create(String method) {
        return create(method, DEFAULT_POOL_SIZE);
    }

    public static RequestLimiter create(String method, int poolSize) {
        RequestLimiter requestLimiter = new RequestLimiter();
        requestLimiter.setPoolSize(poolSize);
        requestLimiter.setMethod(method);
        return requestLimiter;
    }

    public void consume() {
        synchronized (mutex()) {
            --poolSize;
            if (poolSize < 0) {
                throw Result.toBizException(ResultCodeEnum.REQUEST_OFTEN);
            }
        }
    }

    public void free() {
        synchronized (mutex()) {
            ++poolSize;
        }
    }

    public String mutex() {
        String mutex = this.method;
        if (mutex == null) {
            synchronized (this) {
                mutex = this.method;
                if (mutex == null) {
                    this.method = mutex = DEFAULT_METHOD_POOL;
                }
            }
        }
        return mutex;
    }
//
//    public final void setPoolSize(int poolSize) {
//        synchronized (mutex()) {
//            this.poolSize = poolSize;
//        }
//    }
//
//    public final int getPoolSize() {
//        synchronized (mutex()) {
//            return poolSize;
//        }
//    }
}
