/*
package com.xxx.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoadListener implements ServletContextListener {


    //上下文初始化方法：服务器启动方法
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        //ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        //将spring 配置文件对象存储到servletcontext中
        ServletContext servletContext = servletContextEvent.getServletContext();
        String applicationName = servletContext.getInitParameter("contextConfigLocation");
        ApplicationContext app = new ClassPathXmlApplicationContext(applicationName);
        servletContext.setAttribute("app",app);
        System.out.println("监听上下文初始化完成.....");
    }

    //上下文销毁方法：服务器销毁方法
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("监听上下文注销完成.....");
    }
}
*/
