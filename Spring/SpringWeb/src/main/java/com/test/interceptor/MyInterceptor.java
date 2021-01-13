package com.test.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {

    /**
     * 在Controller目标方法执行之前执行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //System.out.println("preHandle......");
        String param = request.getParameter("param");
        //注意顺序，如param.equals("yes"),没有param，报空指针
       /* if("yes".equals(param)){
            return  true;
        }else{
            request.getRequestDispatcher("/error.jsp").forward(request,response);
            return false;
        }*/

        //返回false,后面目标方法不执行
        return true;
    }

    /**
     * 在Controller目标方法执行之后 ，视图返回之前执行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView  Controller中的ModelAndView , 可以修改
     * @throws Exception
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //修改Controller中的参数
        modelAndView.addObject("name","testInterce");
        //System.out.println("postHandle......");

    }

    /**
     * 在流程执行完毕（视图返回）后执行
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //System.out.println("afterCompletion......");
    }
}