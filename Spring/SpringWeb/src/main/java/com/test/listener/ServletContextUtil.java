package com.test.listener;

import org.springframework.context.ApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;

public class ServletContextUtil {

         public static ApplicationContext getWebApplicationContext(ServletContext servletContext){

             ApplicationContext app = (ApplicationContext) servletContext.getAttribute("app");

             return app;
         }
}
