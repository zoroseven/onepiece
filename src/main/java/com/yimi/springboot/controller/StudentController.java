package com.yimi.springboot.controller;

import com.yimi.springboot.entity.Student;
import com.yimi.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

}
