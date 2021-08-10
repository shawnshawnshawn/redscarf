package com.baiye.redscarf.common.util;

import com.baiye.redscarf.common.model.base.AuthID;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 请不要随意修改，如要添加元素，请创建新的类继承该类，在新的类中添加
 *
 * @author 白也
 * @date 2020/11/17 3:55 下午
 */
public class AopUtils {


    public static AuthID getAuthId(ProceedingJoinPoint point) {
        if (point == null) {
            return null;
        }
        AuthID id = null;
        Object[] args = point.getArgs();
        for (Object arg : args) {
            if (null == arg) {
                continue;
            }
            if (arg instanceof AuthID) {
                id = (AuthID) arg;
                break;
            }
        }
        return id;
    }

}
