package com.spring.controller;

import com.spring.domain.Role;
import com.spring.service.RoleService;
import com.spring.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {

       @Autowired
       private RoleService roleService;

       public void setRoleService(RoleService roleService) {
              this.roleService = roleService;
       }

       @RequestMapping("/roleList")
       public ModelAndView getRoleList(){
               ModelAndView modelAndView  = new ModelAndView();
               List<Role> roleList  = roleService.getRoleList();
               modelAndView.addObject("roleList",roleList);
               modelAndView.setViewName("role-list");
               return  modelAndView;
       }


       @RequestMapping("/addRole")
       public String addRole(Role role, HttpServletRequest request) throws UnsupportedEncodingException {
               request.setCharacterEncoding("UTF-8");
               roleService.addRole(role);
               return "redirect:/role/roleList";
       }
}
