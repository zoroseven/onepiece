package com.zoro.springboot.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @date 2018/9/3  16:57
 */
@Slf4j
@Component
public class KafkaConsumer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @KafkaListener(topics = "test")
    public void testConsume(String msg){
        log.info("test消费消息：{}",msg);
    }

    @KafkaListener(topics = "app_log")
    public void testConsume1(String msg){
        log.info("app_log消费消息：{}",msg);
    }
}
