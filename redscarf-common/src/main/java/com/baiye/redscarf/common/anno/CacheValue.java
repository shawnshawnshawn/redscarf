package com.baiye.redscarf.common.anno;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="jiangliuer_shawn@outlook.com">zhangyingdong</a>
 * @Date 2020/5/14 下午6:04
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CacheValue {
    /**
     * 支持Spel表达式
     */
    String key() default "";

    String name() default "";

    long expire() default 300L;

    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
