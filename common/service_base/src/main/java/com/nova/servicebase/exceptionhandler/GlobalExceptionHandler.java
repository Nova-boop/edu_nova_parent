package com.nova.servicebase.exceptionhandler;

import com.nova.commonutils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理类
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 全局统一异常处理类
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.error().message("执行了全局异常...");
    }

    // 特定异常 ArithmeticException
    @ResponseBody
    @ExceptionHandler(ArithmeticException.class)
    public Result error(ArithmeticException e) {
        e.printStackTrace();
        return Result.error().message("执行了ArithmeticException异常...");
    }

    // 自定义异常处理
    @ResponseBody
    @ExceptionHandler(NovaException.class)
    public Result error(NovaException e) {
        e.printStackTrace();
        return Result.error().code(e.getCode()).message(e.getMsg());
    }

}
