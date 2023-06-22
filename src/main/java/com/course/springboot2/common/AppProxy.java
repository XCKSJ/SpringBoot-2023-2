package com.course.springboot2.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AppProxy {
    @Before(value = "execution(* com.course.springboot2.common.App.add(..))")
    public void before(){
        System.out.println("before......");
    }

    @After(value = "execution(* com.course.springboot2.common.App.add(..))")
    public void after(){
        System.out.println("after......");
    }

    @AfterReturning(value = "execution(* com.course.springboot2.common.App.add(..))")
    public void afterReturning(){
        System.out.println("afterReturning......");
    }

    @AfterThrowing(value = "execution(* com.course.springboot2.common.App1.add(..))")
    public void afterThrowing(){
        System.out.println("afterThrowing......");
    }

    @Around(value = "execution(* com.course.springboot2.common.App2.add(..))")
    public void around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("环绕之前......");
        point.proceed();
        System.out.println("环绕之后......");
    }
}
