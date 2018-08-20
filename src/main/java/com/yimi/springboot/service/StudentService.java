package com.yimi.springboot.service;

import com.yimi.springboot.entity.Student;
import com.yimi.springboot.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public List<Student> getStudentList()
    {
        return studentMapper.getStudentList();
    }
}
