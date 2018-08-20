package com.zoro.springboot.controller;

import com.zoro.springboot.constant.Result;
import com.zoro.springboot.entity.Student;
import com.zoro.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/list")
    public List<Student> getStudentList()
    {
        return studentService.getStudentList();
    }

    @RequestMapping("/add")
    public Result addStudent(@RequestBody Student student)
    {
        return studentService.addStudent(student);
    }

}
