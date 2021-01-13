package com.demo.config;

import com.demo.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

       //1、注册拦截器
      public MyInterceptor myInterceptor(){
          return  new MyInterceptor();
      }
       //添加拦截器到拦截器链中
      public void addInterceptors(InterceptorRegistry registry){
            registry.addInterceptor(myInterceptor()).addPathPatterns("/*");
      }

}
