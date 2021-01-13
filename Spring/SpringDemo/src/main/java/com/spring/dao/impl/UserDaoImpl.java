package com.spring.dao.impl;

import com.spring.dao.UserDao;
import com.spring.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Resource
    private JdbcTemplate jdbcTemplate;


    public List<User> getUserList() {
        return jdbcTemplate.query("select * from sys_user",new BeanPropertyRowMapper<User>(User.class));
    }

    public Long addUser(final User user) {
       // Long userId = jdbcTemplate.update("insert into sys_user values (?,?,?,?,?)", null, user.getUsername(), user.getEmail(), user.getPassword(), user.getPhoneNum());
        final PreparedStatementCreator prepared = new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into sys_user values (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);//自动生成主键
                preparedStatement.setObject(1,null);
                preparedStatement.setString(2,user.getUsername());
                preparedStatement.setString(3,user.getEmail());
                preparedStatement.setString(4,user.getPassword());
                preparedStatement.setString(5,user.getPhoneNum());

                return preparedStatement;
            }
        };

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(prepared,keyHolder);
        return keyHolder.getKey().longValue();
    }

    public void addUserRoles(Long id, Long[] roleIdList) {
        for (Long roleId:roleIdList) {
            jdbcTemplate.update("insert into sys_user_role values(?,?)",id,roleId);
        }

    }

    public void delUserRole(Long userId) {
         jdbcTemplate.update("delete  from sys_user_role where userId=?",userId);
    }

    public void delUser(Long userId) {
        jdbcTemplate.update("delete from sys_user where id=?",userId);

    }

    public User getUserById(Long userId) {
        List<User> users = jdbcTemplate.query("select * from sys_user where id=?", new BeanPropertyRowMapper<User>(User.class), userId);
        return users.get(0);
    }

    public void updateUser(User user) {
        jdbcTemplate.update("update sys_user set username=?,email=?,password=?,phoneNum=? where id=?",user.getUsername(),user.getEmail(),user.getPassword(),user.getPhoneNum(),user.getId());
    }

    public void updateUserRole(Long id, Long[] roleId) {
        delUserRole(id);
        addUserRoles(id,roleId);
    }
}
