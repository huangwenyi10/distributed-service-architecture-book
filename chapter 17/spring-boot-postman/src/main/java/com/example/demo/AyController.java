package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：控制层
 * @author ay
 * @date 2019-03-17
 */
@RestController
@Controller
public class AyController {

    @RequestMapping("/say")
    public String say(Model model){
        return "hello ay";
    }

    @PostMapping("/save")
    public String save(Model model, User user){
        System.out.println(model);
        return "save " + user.name + " success!!!";
    }

    class User{
        public User(){}

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
