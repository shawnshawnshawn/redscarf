package com.baiye.redscarf.common.check;

import java.lang.annotation.*;

/**
 * @author 白也
 * @date 2020/12/3 9:59 上午
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CheckParam {

    /**
     * 字段名称
     * @return string
     */
    String name();

    /**
     * 字符长度
     * @return Integer
     */
    int length() default 0;

    /**
     * 对象不为空
     * @return boolean
     */
    boolean notNull() default false;

    /**
     * 字符串不为空
     * @return boolean
     */
    boolean notBlank() default false;

    /**
     * 大于等于-1
     * @return int
     */
    int ge() default -1;

    /**
     * 小于等于-1
     * @return int
     */
    int le() default -1;

    /**
     * 集合中, 数值和字符串都可以
     * 例如"1,2,3"
     */
    String in() default "";

    /**
     * 参数类型
     */
    RegularEnum regularEnum() default RegularEnum.NON;

    /**
     * 正则表达式
     */
    String regular() default "";

    /**
     * 日期格式转换
     */
    String dateFormatChange() default "";
}
