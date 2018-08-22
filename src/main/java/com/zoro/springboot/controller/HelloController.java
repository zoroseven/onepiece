package com.zoro.springboot.controller;

import com.zoro.springboot.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/")
    @ResponseBody
    public String syaHello()
    {
        return "Hello, Springboot!!!";
    }

    @RequestMapping("/index")
    public String index()
    {
        return "index";
    }

    @RequestMapping("/{id}")
    @ResponseBody
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
