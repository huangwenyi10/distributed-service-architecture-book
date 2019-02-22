package com.example.demo.controller;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 描述：JDK Logger测试类
 * @author ay
 * @date 2019-02-01
 */
public class JdkLoggerTest {


    public static Logger logger = Logger.getLogger(JdkLoggerTest.class.getName());

    static{
        Handler consoleHandler =new ConsoleHandler();
        //设置默认日志级别
        consoleHandler.setLevel(Level.SEVERE);
        logger.addHandler(consoleHandler);
    }

    public static void main(String[] args) {

        //设置日志级别
        logger.setLevel(Level.INFO) ;
        //打印日志
        logger.finest("finest log..");
        logger.finer ("finer log..");
        logger.fine ("fine log ..");
        logger.config("config log ..");
        logger.info("info log..");
        logger.warning("warning log .. ");
        logger.severe("severe log ..");
    }
}
