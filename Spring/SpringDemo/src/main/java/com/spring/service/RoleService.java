package com.spring.service;

import com.spring.domain.Role;

import java.util.List;

public interface RoleService {

        List<Role> getRoleList();

        void addRole(Role role);

        List<Role> getRoleByUserId(Long userId);
}
