package com.zoro.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JSPController {

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

    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }

    @RequestMapping("/upload")
    public String upload()
    {
        return "upload";
    }


}
