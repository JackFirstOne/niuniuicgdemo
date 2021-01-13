package com.test.web;



import com.test.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //加载spring配置文件，创建Spring容器
        //ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        ServletContext servletContext = req.getServletContext();
        //2   ServletContext servletContext1 = this.getServletContext();
        //ApplicationContext app = ServletContextUtil.getWebApplicationContext(this.getServletContext());

        WebApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        UserServiceImpl userService = app.getBean(UserServiceImpl.class);
        userService.save();
        System.out.println("spring");
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
