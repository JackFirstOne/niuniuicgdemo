package com.spring.service.impl;

import com.spring.dao.RoleDao;
import com.spring.dao.UserDao;
import com.spring.domain.Role;
import com.spring.domain.User;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    public List<User> getUserList() {
        List<User> userList = userDao.getUserList();
        for (User user:userList) {
              //获取userRole
            Long id = user.getId();
            List<Role> roleList = roleDao.getRoleByUserId(id);
            user.setRoles(roleList);
        }

        return userList;
    }

    public void addUser(User user, Long[] roleIdList) {
        //添加用户
        Long userId =  userDao.addUser(user);
        //添加用户角色关系
        userDao.addUserRoles(userId,roleIdList);
    }

    public void delUser(Long userId) {
        userDao.delUserRole(userId);
        userDao.delUser(userId);
    }

    public User getUserById(Long userId) {
        User user = userDao.getUserById(userId);
        List<Role> roles = roleDao.getRoleByUserId(user.getId());
        user.setRoles(roles);
        return user;
    }

    public void updateUser(User user, Long[] roleId) {
        userDao.updateUser(user);
        userDao.updateUserRole(user.getId(),roleId);
    }
}
