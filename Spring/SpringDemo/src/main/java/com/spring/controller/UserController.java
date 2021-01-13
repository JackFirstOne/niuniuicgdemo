package com.spring.controller;

import com.spring.domain.Role;
import com.spring.domain.User;
import com.spring.service.RoleService;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/userList")
    public ModelAndView getUserList() {

        List<User> userList = userService.getUserList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user-list");
        modelAndView.addObject("userList", userList);
        return modelAndView;
    }

    @RequestMapping("getRoleList")
    public ModelAndView getRoleList() {
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roleList = roleService.getRoleList();
        modelAndView.addObject("roleList", roleList);
        modelAndView.setViewName("user-add");
        return modelAndView;
    }

    @RequestMapping("/addUser")
    public String addUser(User user,Long [] roleId){

        if(user.getId()==null){
            userService.addUser(user,roleId);
        }else{
            userService.updateUser(user,roleId);
        }
        return "redirect:/user/userList";
    }


    @RequestMapping("/delUser/{userId}")
    public String delUser(@PathVariable(value = "userId") Long userId){
        userService.delUser(userId);
        return "redirect:/user/userList";
    }

    @RequestMapping("/getUser/{userId}")
    public ModelAndView updateUser(@PathVariable(value = "userId") Long userId){

        ModelAndView modelAndView = new ModelAndView();

        //获取用户信息
        User user = userService.getUserById(userId);
        modelAndView.addObject("user",user);

        //获取所有角色信息
        List<Role> roleList = roleService.getRoleList();
        modelAndView.addObject("roleList", roleList);

        modelAndView.setViewName("user-add");
        return modelAndView;
    }

}
