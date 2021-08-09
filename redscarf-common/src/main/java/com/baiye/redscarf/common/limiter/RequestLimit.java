package com.baiye.redscarf.common.limiter;

import java.lang.annotation.*;

/**
 * @author baiye
 * @date 2021/5/19 10:01 上午
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestLimit {

    RequestLimitEnum limitEnum() default RequestLimitEnum.METHOD;

    int concurrent() default Integer.MAX_VALUE;
}
