package com.zoro.springboot.service;

import com.zoro.springboot.constant.Result;
import com.zoro.springboot.entity.Student;
import com.zoro.springboot.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public List<Student> getStudentList()
    {
        return studentMapper.getStudentList();
    }

    @Transactional
    public Result addStudent(Student student){
        studentMapper.addStudent(student);
        return new Result("");
    }
}
