package com.zoro.springboot.service;

import com.zoro.springboot.constant.Result;
import com.zoro.springboot.entity.Student;
import com.zoro.springboot.mapper.StudentMapper;
import com.zoro.springboot.mapper.StudentMapper2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Resource //default byName
    private StudentMapper studentMapper;

    @Autowired //default byType
    private StudentMapper2 studentMapper2;

//    @Autowired
    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;

    @Transactional
    public List<Student> getStudentList()
    {
        //studentMapper.getStudentList();
        logger.info("查询学生列表");
        return studentMapper.getStudentList();
    }

    public Student getStudentByNameAndPwd(Student student){
        return studentMapper.getStudentByNameAndPwd(student);
    }

    @Transactional
    public Result addStudent(Student student){
//        studentMapper.addStudent(student);
        redisTemplate.opsForValue().set("age",student.getAge());
        //redisTemplate1.opsForValue().set("name",student.getName());
        //redisTemplate2.opsForValue().set("name",student.getName());
        return new Result("");
    }

    public List<Student> getStudentList2()
    {
        return studentMapper2.getStudentList();
    }
}
