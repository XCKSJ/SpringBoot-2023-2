package com.course.springboot2.domain;

import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String massage;
    private T data;

    public Result() {
    }

    public static <T> Result<T> ok(){
        Result<T> r = new Result<>();
        r.setCode(200);
        r.setMassage("成功");
        return r;
    }

    public static <T> Result<T> ok(String msg){
        Result<T> r = new Result<>();
        r.setCode(200);
        r.setMassage(msg);
        return r;
    }

    public static <T> Result<T> ok(T data){
        Result<T> r = new Result<>();
        r.setCode(200);
        r.setMassage("成功");
        r.setData(data);
        return r;
    }

    public static <T> Result<T> ok(int code, String msg, T data){
        Result<T> r = new Result<>();
        r.setCode(code);
        r.setMassage(msg);
        r.setData(data);
        return r;
    }
}
