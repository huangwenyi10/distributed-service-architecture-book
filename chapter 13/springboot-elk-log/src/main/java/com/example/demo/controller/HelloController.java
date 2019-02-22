package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * 描述：控制层
 * @author ay
 * @date 2019-02-03
 */
@RestController
public class HelloController {

    Logger LOG = Logger.getLogger(HelloController.class.getName());

    @RequestMapping("/hello")
    public String say(){
        System.out.println("hello ay...");
        return "hello ay";
    }
}
