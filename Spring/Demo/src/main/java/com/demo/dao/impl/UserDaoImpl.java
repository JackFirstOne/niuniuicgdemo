package com.demo.dao.impl;

import com.demo.dao.UserDao;
import com.demo.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll() {
        List<User> userList = jdbcTemplate.query("select * from sys_user", new BeanPropertyRowMapper<User>(User.class));
        return userList;
    }

    public Long save(final User user) {
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into sys_user values(?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setObject(1,null);
                preparedStatement.setString(2,user.getUsername());
                preparedStatement.setString(3,user.getEmail());
                preparedStatement.setString(4,user.getPassword());
                preparedStatement.setString(5,user.getPhoneNum());
                return preparedStatement;
            }


        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator,generatedKeyHolder);
        long userId = generatedKeyHolder.getKey().longValue();

        return userId;
    }

    public void saveUserRoleRel(Long id, Long[] roleIds) {
        for (Long roleid:roleIds) {
             jdbcTemplate.update("insert into sys_user_role values(?,?)",id,roleid);
        }
    }

    public void del(Long userId) {
             jdbcTemplate.update("delete from sys_user where id=?",userId);
    }

    public void delUserRoleRel(Long userId) {
           jdbcTemplate.update("delete from sys_user_role where userid=?",userId);
    }

    public User login(String username, String password) {
        User user = (User) jdbcTemplate.query("select * form sys_user where username = ? and password = ?",new BeanPropertyRowMapper<User>(User.class),username,password);
        return user;
    }
}
