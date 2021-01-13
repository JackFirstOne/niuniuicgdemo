package com.test.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义异常
 */
public class MyDemoException implements HandlerExceptionResolver {

    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        ModelAndView modelAndView = new ModelAndView();
        if(e instanceof MyException){
               modelAndView.addObject("info","自定义异常");
        }else if(e instanceof NullPointerException){
               modelAndView.addObject("info","空指针异常");
        }
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
