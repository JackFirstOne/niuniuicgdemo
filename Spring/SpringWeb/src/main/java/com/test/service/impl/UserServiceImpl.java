package com.test.service.impl;

import com.test.dao.UserDao;
import com.test.service.UserService;

public class UserServiceImpl implements UserService {

     private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save() {
            userDao.save();
    }
}
