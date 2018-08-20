package com.zoro.springboot.controller;

import com.zoro.springboot.entity.Student;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class HelloController {

    @RequestMapping("/")
    public String syaHello()
    {
        return "Hello, Springboot!!!";
    }
    @RequestMapping("/{id}")
    public Student getStudent(@PathVariable String id)
    {
        Student student = new Student();
        student.setId(id);
        student.setAge(20);
        student.setName("zhangsan");
        student.setSex("male");
        return student;
    }

}
