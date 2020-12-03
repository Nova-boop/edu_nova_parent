package com.nova.commonutils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

// 统一返回结果类
@Data
public class Result {

    private Boolean success;
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<String, Object>();


    // 链式编程
    // R.ok().success().data()
    // 把构造方法私有化
    private Result() {
    }


    // 成功静态方法
    public static Result ok() {
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("success");
        return r;
    }

    // 失败静态方法
    public static Result error() {
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("error");
        return r;
    }

    public Result success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public Result message(String message) {
        this.setMessage(message);
        return this;
    }

    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }

    public Result data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }


}
