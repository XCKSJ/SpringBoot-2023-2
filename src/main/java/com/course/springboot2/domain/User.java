package com.course.springboot2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private String email;

    public User(){}
}
