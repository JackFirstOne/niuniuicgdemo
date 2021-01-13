package com.demo.dao;

import com.demo.domain.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();
    Long save(User user);
    void saveUserRoleRel(Long id,Long [] roleIds);
    void del(Long userId);
    void delUserRoleRel(Long userId);

    User login(String username, String password);
}
