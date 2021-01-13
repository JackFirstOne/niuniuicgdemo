package com.test.controller;

import com.test.exception.MyException;
import com.test.service.DemoService;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.FileNotFoundException;

@Controller
public class TargetController {


    @Autowired
    private DemoService demoService;



    @RequestMapping("/target")
       public ModelAndView show(){

           System.out.println("目标资源执行......");
           ModelAndView modelAndView = new ModelAndView();
           modelAndView.addObject("name","ceshi");
           modelAndView.setViewName("index");
           return  modelAndView;
       }

    @RequestMapping("/exception")
    public String  show2() throws FileNotFoundException, MyException {

        System.out.println("show2 running......");
        //demoService.show4() ;
        demoService.show5();
        return  "index";
    }

}
