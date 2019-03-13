package com.example.demo;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 描述：用户服务
 * @author ay
 * @date 2019-03-11
 */
@Component
public class UserServiceImpl implements UserService{

    @Resource
    private UserDao userDao;

//    @Override
//    public AyUser findUser(String id) {
//        AyUser ayUser = new AyUser();
//        ayUser.setId(1);
//        ayUser.setName("ay");
//        return ayUser;
//    }

    @Override
    public AyUser findUser(Integer id) {
        AyUser ayUser = userDao.findUser(id);
        return ayUser;
    }

    @Override
    public boolean deleteUser(Integer id) {
        boolean isSuccess = userDao.deleteUser(id);
        return isSuccess;
    }
}


