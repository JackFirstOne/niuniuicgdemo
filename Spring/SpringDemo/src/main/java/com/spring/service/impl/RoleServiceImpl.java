package com.spring.service.impl;

import com.spring.dao.RoleDao;
import com.spring.domain.Role;
import com.spring.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    public List<Role> getRoleList() {

        return roleDao.getRoleList();
    }

    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    public List<Role> getRoleByUserId(Long userId) {
        return roleDao.getRoleByUserId(userId);
    }
}
