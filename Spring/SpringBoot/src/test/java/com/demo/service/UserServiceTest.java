package com.demo.service;

import com.demo.pojo.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @org.junit.Test
    public void getUserById() {
        User userById = userService.getUserById(10);
        System.out.println(userById);
    }

    @org.junit.Test
    public void saveUser() {
         User user = new User();
         user.setName("测试");
         user.setAge(19);
         user.setBirthday(new Date());
         user.setPassword("123456");
         user.setCreated(new Date());

         userService.saveUser(user);
    }
}