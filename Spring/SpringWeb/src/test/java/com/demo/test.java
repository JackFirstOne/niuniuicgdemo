package com.demo;

import com.test.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class test {

      @Test
      public void test1() throws IOException {

             //获取核心配置文件
          InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfigs.xml");
          SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
          SqlSession sqlSession = build.openSession();
          List<User> userList = sqlSession.selectList("UserMapper.findAll");
          System.out.println(userList);
          sqlSession.close();
      }
}
