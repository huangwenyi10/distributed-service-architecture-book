package com.example.demo.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 *
 * @author ay
 * @date 2019-03-24
 */
@RestController
public class HelloController {


    @RequestMapping(value = "/hello")
    @HystrixCommand(fallbackMethod = "fallback_hello", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public String hello() throws InterruptedException {
        Thread.sleep(3000);
        return "Hello Hystrix";
    }

    /**
     * 请求失败，调用方法
     */
    private String fallback_hello() {
        return "Request fails. It takes long time to response";
    }
}
