package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：控制层
 * @author ay
 * @date 2019-04-17
 */
@RestController
public class HelloController {

    @Value("${config.name}")
    private String notify;

    @RequestMapping("/hello")
    public String hello(){
        return notify;
    }
}