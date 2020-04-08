package com.shopping.util;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * 返回工具类
 * @Auther: wangjian
 * @Date: 2019-03-13 15:59:19
 */
public class Result<T> implements Serializable {

    private String code;

    private String message;

    private T data;

    public Result() {
    }

    public static final String ERROR_CODE = "-1";

    public static final String SUCCESS_CODE = "0";



    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public Result<T> error(String code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    public Result<T> success(T data) {
        this.code = "0";
        this.data = data;
        this.message = "SUCCESS";
        return this;
    }

    public Result<T> systemError() {
        this.code = ERROR_CODE;
        this.message = "系统异常";
        return this;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
