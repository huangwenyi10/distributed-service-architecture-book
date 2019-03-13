package com.example.demo;

/**
 * 描述：用户实体类
 * @author ay
 * @date 2019-03-11
 */
public class AyUser {

    private Integer id;

    private String name;

    public AyUser(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public AyUser(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
