package com.course.springboot2.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class TestException {
    @RequestMapping("/div")
    public String div(@RequestParam("p1") String p1, @RequestParam("p2") String p2){
        int a = Integer.parseInt(p1);
        int b = Integer.parseInt(p2);
        int c = 0;
        c = a / b;

        return p1 + " / " + p2 + " = " + c;
    }
}
