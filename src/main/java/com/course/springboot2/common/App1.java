package com.course.springboot2.common;

import org.springframework.stereotype.Component;

@Component
public class App1 {
    public void add(){
        int a = 2, b = 0, c;
        c = a/b;
        System.out.println("throw......");
    }
}
