package com.zoro.springboot.controller;

import com.zoro.springboot.constant.ResultRes;
import com.zoro.springboot.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date 2018/9/3  16:55
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @RequestMapping("/appLog")
    public ResultRes kafkaAppLog(@RequestParam("msg")String msg){
        kafkaProducer.sendAppLog(msg);
        return ResultRes.success("");
    }

    @RequestMapping("/test")
    public ResultRes kafkaTest(@RequestParam("msg")String msg){
        kafkaProducer.sendTest(msg);
        return ResultRes.success("");
    }
}
