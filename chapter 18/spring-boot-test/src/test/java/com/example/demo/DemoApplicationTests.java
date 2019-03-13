package com.example.demo;
import static org.mockito.Mockito.*;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

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

        AyUser ayUser = userService.findUser(1);
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



    @Test
    public void testMockito_1() {
        List mock = mock(List.class);
        when(mock.get(0)).thenReturn("ay");
        when(mock.get(1)).thenReturn("al");
        //测试通过
        Assertions.assertThat(mock.get(0)).isEqualTo("ay");
        //测试不通过
        Assertions.assertThat(mock.get(1)).isEqualTo("xx");
    }

    @Test
    public void testMockito_2(){
        UserService userService = mock(UserService.class);
        when(userService.findUser(1)).thenReturn(new AyUser(1, "ay"));
        //通过mock，查询出模拟用户对象
        AyUser ayUser = userService.findUser(1);
        //删除用户
        boolean isSuccess = userService.deleteUser(ayUser.getId());

        Assertions.assertThat(isSuccess).isFalse();
    }

}
