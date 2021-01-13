package com.demo.service;

import com.demo.mapper.UserMapper;
import com.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

      @Autowired
      private UserMapper userMapper;

      //根据Id查询
      public User getUserById(Integer id){
           return userMapper.selectByPrimaryKey(id);
      }

      //新增用户
     @Transactional
     public void saveUser(User user){
         System.out.println(user+",新增用户...");
         //选择性新增，当有属性为空，可以选择性新增，属性不会出现在insert sql语句中
         userMapper.insertSelective(user);

     }

}
