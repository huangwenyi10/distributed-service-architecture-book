package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：控制层类
 * @author ay
 * @date 2019-02-01
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public void say(){
        System.out.println("hello ay");
    }

}
