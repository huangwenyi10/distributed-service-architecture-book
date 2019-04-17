package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：hello 控制层
 *
 * @author ay
 * @date 2019-03-25
 */
@RestController
@RequestMapping("/hello")
public class HelloController {


    @RequestMapping("say")
    public String hello(){
        System.out.println("say hello!!!");
        return "hello ay!!!";
    }

}
