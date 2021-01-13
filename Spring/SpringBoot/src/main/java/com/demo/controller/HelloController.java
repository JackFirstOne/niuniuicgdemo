package com.demo.controller;


import com.demo.pojo.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * 组合注解
 * @Controller
 * @ResponseBody
 * 所有方法返回结果为字符
 */
@RestController
public class HelloController {

         @Autowired
         private DataSource dataSource;

         @Value("${demo.url}")
         private String demoUrl;

        @Value("${demo2.url}")
        private String demo2Url;

        @Autowired
        private UserService userService;


        @RequestMapping("user/{id}")
        public User selById(@PathVariable(name = "id") Integer id){
             return userService.getUserById(id);
        }

        @RequestMapping(method = RequestMethod.GET,value = "/hello")
        public String hello(){
            System.out.println(dataSource+"------");
            System.out.println(demo2Url);
            System.out.println(demoUrl);
            return "Hello Spring-boo";
        }
}
