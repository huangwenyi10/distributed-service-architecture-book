package com.example.demo;

import org.springframework.stereotype.Component;

/**
 * 描述：用户服务
 * @author ay
 * @date 2019-03-11
 */
@Component
public class UserServiceImpl implements UserService{


    @Override
    public AyUser findUser(String id) {
        AyUser ayUser = new AyUser();
        ayUser.setId(1);
        ayUser.setName("ay");
        return ayUser;
    }
}


