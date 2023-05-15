package com.course.springboot2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
@RequestMapping("/test")
public class ForwardAndRedirectController {
    @RequestMapping("/testForward")
    public String testForward(){
        return "forward:/user/hello";
    }
    @RequestMapping("/testRedirect1")
    public String testRedirect1(){
        return "redirect:https://www.cque.edu.cn";
    }
    @RequestMapping("/testRedirect2")
    public String testRedirect2(){
        return "redirect:/user/hello";
    }
}
