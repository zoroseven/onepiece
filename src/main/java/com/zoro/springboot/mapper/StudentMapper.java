package com.zoro.springboot.mapper;

import com.zoro.springboot.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> getStudentList();

    int addStudent(Student student);
}
