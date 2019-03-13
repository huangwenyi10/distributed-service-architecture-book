package com.example.demo;
import org.springframework.stereotype.Component;

/**
 * 描述：UserDao
 * @author ay
 * @date 2019-03-13
 */
@Component
public class UserDao {

    public AyUser findUser(Integer userId){
        AyUser user = null;//查询数据库
        return user;
    }

    public boolean deleteUser(Integer userId){
        //操作数据库
        return true;
    }
}
