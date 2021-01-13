package com.spring.dao;

import com.spring.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RoleDao {

    List<Role> getRoleList();

    void addRole(Role role);

    List<Role> getRoleByUserId(Long id);
}
