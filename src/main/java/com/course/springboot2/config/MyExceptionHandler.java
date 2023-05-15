package com.course.springboot2.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler
    public String exceptionHandler(Exception e){
        System.out.println(e.getMessage());
        return e.getMessage();
    }
}
