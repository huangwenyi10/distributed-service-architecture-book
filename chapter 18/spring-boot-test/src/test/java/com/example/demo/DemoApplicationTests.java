package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Resource
    private UserService userService;

    @Test
    public void contextLoads() {}


    @Test
    public void testFindUser(){
        boolean success = false;
        int num = 10;

        AyUser ayUser = userService.findUser("1");
        //JUnit断言
        Assert.assertNotNull("user is null",ayUser);
        //AssertJ断言
        Assertions.assertThat(ayUser).isNotNull();

        //JUnit断言
        Assert.assertTrue("result is not true", success);
        //AssertJ断言
        Assertions.assertThat(success).isTrue();

        //JUnit断言
        Assert.assertEquals("num is not equal 10", 10, num);
        //AssertJ断言
        Assertions.assertThat(num).isEqualTo(10);

    }

}
