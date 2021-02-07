package com.demo.service;

import com.demo.mapper.UserMapper;
import com.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

     @Autowired
     private UserMapper userMapper;

     public User getUserById(Integer id){
         User user = userMapper.selectByPrimaryKey(id);
         return  user;
     }
}
