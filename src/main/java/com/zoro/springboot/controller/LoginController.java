package com.zoro.springboot.controller;

import com.zoro.springboot.entity.Student;
import com.zoro.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gaocheng
 * @date 2018/8/24  7:26
 */
@RestController
public class LoginController {

	@Autowired
	StudentService studentService;

	@RequestMapping("/login")
	public String login(Student student){
		return "login";
	}
}
