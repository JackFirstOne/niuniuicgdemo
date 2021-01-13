package com.spring.dao;

import com.spring.domain.User;

import java.util.List;

public interface UserDao {
    List<User> getUserList();

    Long addUser(User user);

    void addUserRoles(Long id, Long[] roleIdList);

    void delUserRole(Long userId);

    void delUser(Long userId);

    User getUserById(Long userId);

    void updateUser(User user);

    void updateUserRole(Long id, Long[] roleId);
}
