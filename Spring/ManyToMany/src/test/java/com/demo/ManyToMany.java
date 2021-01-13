package com.demo;


import com.demo.dao.RoleDao;
import com.demo.dao.UserDao;
import com.demo.domain.Role;
import com.demo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


//配置spring单元测试
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ManyToMany {

      @Autowired
      private UserDao userDao;
      @Autowired
      private RoleDao roleDao;

    /**
     * 多对多放弃维护权：
     * 被动方放弃维护权:角色放弃维护权
     */
    @Test
      @Transactional
      @Rollback(value = false)
      public void test1(){
          User user = new User();
          user.setAge(13l);
          user.setUsername("张三");

          Role role = new Role();
          role.setRoldName("经理");

           user.getRoles().add(role);//配置用户到角色的关系
           role.getUsers().add(user);//配置角色到用户的关系

          userDao.save(user);
          roleDao.save(role);
      }

    /**
     * 测试级联添加:在user表中manyToMany添加 cascad
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testCasCadaAdd(){
        User user = new User();
        user.setAge(13l);
        user.setUsername("张三");

        Role role = new Role();
        role.setRoldName("经理");

        user.getRoles().add(role);//配置用户到角色的关系
        role.getUsers().add(user);//配置角色到用户的关系

        userDao.save(user);
        //roleDao.save(role);
    }

    /**
     * 级联删除
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testCasCadaDel(){
        User one = userDao.findOne(1l);
        userDao.delete(one);
    }
}
