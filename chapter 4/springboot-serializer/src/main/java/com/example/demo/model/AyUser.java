package com.example.demo.model;
import java.io.Serializable;

/**
 * 描述：用户类
 * @author ay
 * @date 2019-01-27
 */
public class AyUser implements Serializable {

    private static final long serialVersionUID = 7110894678803247099L;

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "AyUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
