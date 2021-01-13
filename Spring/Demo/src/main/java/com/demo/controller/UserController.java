package com.demo.controller;

import com.demo.domain.User;
import com.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("user")
public class UserController {

       @Resource
       private UserService userService;


       @RequestMapping("/login")
       public String Login(String username, String password, HttpSession session){

               User user = userService.login(username,password);
               if(user != null){
                      session.setAttribute("user",user);
                      return "redirect:/index.jsp";
               }
               return "redirect:/login.jsp";
       }

}
