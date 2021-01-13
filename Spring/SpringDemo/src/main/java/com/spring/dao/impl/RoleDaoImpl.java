package com.spring.dao.impl;

import com.spring.dao.RoleDao;
import com.spring.domain.Role;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Resource
    private JdbcTemplate jdbcTemplate;


    public List<Role> getRoleList() {
        return jdbcTemplate.query("select * from sys_role",new BeanPropertyRowMapper<Role>(Role.class));
    }

    public void addRole(Role role) {
       jdbcTemplate.update("insert into sys_role values(?,?,?)",null,role.getRoleName(),role.getRoleDesc());
    }

    public List<Role> getRoleByUserId(Long id) {
        return jdbcTemplate.query("select * from sys_user_role ur,sys_role role where ur.roleId = role.id and ur.userId = ?",new BeanPropertyRowMapper<Role>(Role.class),id);
    }
}
