package com.baiye.redscarf.common.anno;

import java.lang.annotation.*;

/**
 * @author <a href="jiangliuer_shawn@outlook.com">zhangyingdong</a>
 * @Date 2020/5/14 下午6:03
 */

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheClear {

    String name() default "";

    String key() default "";

    String keyPrefix() default "";
}
