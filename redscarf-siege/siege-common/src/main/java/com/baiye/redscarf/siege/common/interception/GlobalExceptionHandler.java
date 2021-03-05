package com.baiye.redscarf.siege.common.interception;

import com.baiye.redscarf.common.enums.ResultCodeEnum;
import com.baiye.redscarf.common.exception.BizException;
import com.baiye.redscarf.common.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * 全局异常处理器
 *
 * @author <a href="jiangliuer_shawn@outlook.com">zhangyingdong</a>
 * @date 2020/5/18 上午11:01
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger err_log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    static {
        err_log.info("load global exception handler");
    }


//    @Resource
//    private DingTalkPublish dingTalkPublish;
//
//    @Resource
//    private DingTalkProperties dingTalkProperties;

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Result<Object> exceptionHandle(Exception e) {
        if (e instanceof BizException) {
            BizException be = (BizException) e;
            return Result.ofFail(be.getStatus(), be.getMessage());
        } else if (e instanceof BindException) {
            return Result.ofFail(1, Objects.requireNonNull(((BindException) e).getBindingResult().getFieldError()).getDefaultMessage());
        } else if (e instanceof MethodArgumentNotValidException) {
            return Result.ofFail(1, Objects.requireNonNull(((MethodArgumentNotValidException) e).getBindingResult().getFieldError()).getDefaultMessage());
        } else if (e instanceof HttpMessageNotReadableException) {
            err_log.error("捕捉系统异常 {}, e -> ", e.getMessage(), e);
            return Result.ofError(ResultCodeEnum.PARAM_NULL);
        } else {
            publishErrorMsg(e);
            err_log.error("捕捉系统异常 {}, e -> ", e.getMessage(), e);
            return Result.ofError(ResultCodeEnum.SERVER_ERROR);
        }
    }

    private void publishErrorMsg(Exception e) {
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            String stack = stackTraceElement.toString();
            if (stack.startsWith("com.cheapp")) {
                if (stack.contains("$$")) {
                    stack = stack.split("\\$")[0];
                }
                sb.append("at ").append(stack).append("\n");
            }
        }
//        String message = "[服务器异常]"
//                + "\n产品名称：" + dingTalkProperties.getAppSysName()
//                + "\n系统环境：" + SysEnvTypeEnum.getSysEnvType(dingTalkProperties.getEnv()).getMsg()
//                + "\n异常名称：" + e.getClass().getName()
//                + "\n异常信息：" + e.getMessage()
//                + "\n异常栈列：" + sb;
//        DingTalkMessage dingTalkMessage = new DingTalkMessage();
//        dingTalkMessage.setMessage(message);
//        dingTalkMessage.setChatId(dingTalkProperties.getErrorChatId());
//        dingTalkPublish.publish(dingTalkMessage);
    }
}
