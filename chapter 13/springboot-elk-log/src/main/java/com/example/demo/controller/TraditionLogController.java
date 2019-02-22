package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * 描述：传统日志控制层
 * @author ay
 * @date 2019-02-03
 */
@RestController
@RequestMapping("tradition")
public class TraditionLogController {

    Logger logger = Logger.getLogger(TraditionLogController.class.getName());

    @RequestMapping("/log")
    public String say(String param1, String param2){
        logger.info("class:TraditionLogController and method:say and the param1 is :" +
            param1 +  " the param2 is :" + param2);
        return "tradition log...";
    }
}
