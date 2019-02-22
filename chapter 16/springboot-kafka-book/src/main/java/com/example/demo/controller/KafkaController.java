package com.example.demo.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 描述：kafka控制层
 * @date 2019-01-17
 * @author ay
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Resource
    private KafkaTemplate kafkaTemplate;

    @RequestMapping("/sendMsg")
    public void sendMessage(Model model){
        System.out.println("product seed message: hello, ay");
        kafkaTemplate.send("spring-kafka-topic", "hello, ay");
        System.out.println("send message success!!!");
    }
}
