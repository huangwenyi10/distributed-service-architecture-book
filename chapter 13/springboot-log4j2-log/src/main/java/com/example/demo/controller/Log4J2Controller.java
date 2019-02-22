package com.example.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：log4j2 控制层
 * @author ay
 * @date 2019-02-01
 */
@RestController
public class Log4J2Controller {

    Logger logger = LogManager.getLogger(this.getClass().getName());

    @RequestMapping("/log4j2")
    public String log4j2(){
        //debug日志，级别低于info日志，不打印
        logger.debug("debug--->>>class:Log4J2Controller and method:log4j2");
        //info日志
        logger.info("info--->>>class:Log4J2Controller and method:log4j2");
        return "log4j2";
    }
}
