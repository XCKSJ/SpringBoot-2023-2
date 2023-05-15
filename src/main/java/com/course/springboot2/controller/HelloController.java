package com.course.springboot2.controller;

import com.course.springboot2.domain.User;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello, SpringBoot...!!!";
    }

    @GetMapping("/getUser")
    public String h1(String name, int age){
        //System.out.println("2");
        return "Hello, " + name + "==>" + age;
    }
    
    @PostMapping("/addUser")
    public String h2(@RequestBody User user){
        return "Post ..., " + user;
    }
}
