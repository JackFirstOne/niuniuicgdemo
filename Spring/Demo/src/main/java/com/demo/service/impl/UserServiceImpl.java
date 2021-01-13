package com.demo.service.impl;

import com.demo.dao.UserDao;
import com.demo.domain.User;
import com.demo.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> findAllUser() {
        return userDao.findAll();
    }

    public void save(User user, Long [] roleIds) {
        Long userid = userDao.save(user);
        userDao.saveUserRoleRel(userid,roleIds);
    }

    public void del(Long userId) {

           userDao.delUserRoleRel(userId);
           userDao.del(userId);
    }

    public User login(String username, String password) {

         User user= userDao.login(username,password);
        return user;
    }
}
