package com.zoro.springboot.service;

import com.zoro.springboot.constant.Result;
import com.zoro.springboot.entity.Student;
import com.zoro.springboot.mapper.StudentMapper;
import com.zoro.springboot.mapper.StudentMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentService {

    @Resource //default byName
    private StudentMapper studentMapper;

    @Autowired //default byType
    private StudentMapper2 studentMapper2;

    @Transactional
    public List<Student> getStudentList()
    {
        studentMapper.getStudentList();
        return studentMapper.getStudentList();
    }

    @Transactional
    public Result addStudent(Student student){
        studentMapper.addStudent(student);
        return new Result("");
    }

    public List<Student> getStudentList2()
    {
        return studentMapper2.getStudentList();
    }
}
