package com.nova.servicebase.exceptionhandler;

import com.nova.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    // 全局统一异常处理类
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("执行了全局异常...");
    }

    // 特定异常 ArithmeticException
    @ResponseBody
    @ExceptionHandler(ArithmeticException.class)
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常...");
    }

    // 自定义异常处理
    @ResponseBody
    @ExceptionHandler(NovaException.class)
    public R error(NovaException e) {
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }

}
