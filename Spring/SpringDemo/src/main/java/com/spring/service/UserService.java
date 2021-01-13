package com.spring.service;

import com.spring.domain.User;


import java.util.List;

public interface UserService {

        List<User> getUserList();

        void addUser(User user, Long[] roleIdList);

        void delUser(Long userId);

        User getUserById(Long userId);

        void updateUser(User user, Long[] roleId);
}
