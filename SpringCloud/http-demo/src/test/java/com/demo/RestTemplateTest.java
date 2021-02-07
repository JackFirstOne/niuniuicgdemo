package com.demo;

import com.demo.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTemplateTest {

       @Autowired
       private RestTemplateTest restTemplateTest;

       @Test
       public void test1(){
              String url = "http://localhost/user/8";
              //restTemplateTest 可以对json格式字符串进行反序列化
              User user =  restTemplateTest.getForObjcet(url, User.class);
              System.out.println(user);
       }
}
