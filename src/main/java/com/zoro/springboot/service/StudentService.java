package com.zoro.springboot.service;

import com.alibaba.fastjson.JSON;
import com.zoro.springboot.constant.ResultRes;
import com.zoro.springboot.entity.Student;
import com.zoro.springboot.mapper.StudentMapper;
import com.zoro.springboot.mapper.StudentMapper2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class StudentService {

    //private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

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
        List<Student> studentList = studentMapper.getStudentList();
        log.info("查询学生列表");
        redisTemplate.opsForValue().set("stuList", JSON.toJSONString(studentList));
        return studentList;
    }

    public Student getStudentByNameAndPwd(Student student){
        return studentMapper.getStudentByNameAndPwd(student);
    }

    //@Transactional
    public ResultRes addStudent(Student student){
        studentMapper.addStudent(student);
        redisTemplate.opsForValue().set("age",student.getAge());
        return ResultRes.success(null);
    }

    public List<Student> getStudentList2()
    {
        return studentMapper2.getStudentList();
    }
}
