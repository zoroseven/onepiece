package com.zoro.springboot.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @date 2018/9/3  16:54
 */
@Component
@Slf4j
public class KafkaProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendAppLog(String msg){
        ListenableFuture future = kafkaTemplate.send("app_log", msg);
        future.addCallback(o -> log.info("send-消息发送成功：" + msg), throwable -> log.error("消息发送失败：{}" , msg , throwable.getStackTrace()));
    }

    public void sendTest(String msg){
        ListenableFuture future = kafkaTemplate.send("test", msg);
        future.addCallback(o -> log.info("send-消息发送成功：" + msg), throwable -> log.error("消息发送失败：{}" , msg , throwable.getStackTrace()));
    }
}
