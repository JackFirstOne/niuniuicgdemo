package com.test.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.entity.User;
import com.test.entity.VO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/hello",method = RequestMethod.GET,params = {"username"})
    public String save(){
        System.out.println("Controller save start......");
        //return "forward:/jsp/hello.jsp"; //默认为转发，地址不变，forward:可省略
        //return "redirect:/hello.jsp"; //重定向：地址改变
        return "hello";

    }

    /**
     * 返回视图方式2：返回ModelAndView
     * @return
     */
    @RequestMapping("/hello2")
    public ModelAndView save2(){
        ModelAndView modelAndView = new ModelAndView();
        //设置视图
        modelAndView.setViewName("hello");
        //设置数据
        modelAndView.addObject("username","张三");
        return modelAndView;
    }


    @RequestMapping("hello3")
    public ModelAndView save3(ModelAndView modelAndView){
        modelAndView.setViewName("hello");
        modelAndView.addObject("username","李四");
        return modelAndView;

    }

    @RequestMapping("hello4")
    public String save4(Model model){
        model.addAttribute("username","王五");
        return "hello";
    }

    @RequestMapping("hello5")
    public String save5(Model model, HttpServletRequest request){
        request.setAttribute("username","刘六");
        return "hello";
    }

    /**
     * 响应数据
     * @param model
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("hello6")
    public void save6(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("hello World");
    }

    /**
     * 直接返回字符串，需要加 @ResponseBody，不会页面跳转，返回响应参数
     * @return
     */
    @RequestMapping("hello7")
    @ResponseBody
    public String save7() {
        return  "hello World hello7";
    }

    /**
     * 直接返回json字符串
     * @return
     */
    @RequestMapping("hello8")
    @ResponseBody
    public String save8() {
        return  "{\"username\":\"张三\",\"age\":\"19\"}";
    }


    /**
     * 对象转json
     * @return
     * @throws IOException
     */
    @RequestMapping("hello9")
    @ResponseBody
    public String save9() throws IOException {
        User user = new User();
        //user.setAge(20);
        user.setUsername("历史");
        //使用json转换工具将对象转换为json
        ObjectMapper objectMapper =new ObjectMapper();
        String userStr = objectMapper.writeValueAsString(user);

        return userStr;
    }

    /**
     *
     * 配置mvc:annotation-driver
     * 返回对象，配置HandlerAdapter(RequestMappingHandlerAdapter),   spring自动将对象转换为字符串
     * @return
     * @throws IOException
     */
    @RequestMapping("hello10")
    @ResponseBody
    public User save10() throws IOException {
        User user = new User();
        //user.setAge(20);
        user.setUsername("历史");

        return user;
    }

    //接收客户端数据
    //1、基本数据类型   2、pojo类型数据  3、数组类型参数  4、集合类型参数

    @RequestMapping("hello11")
    @ResponseBody
    public void save11(String username,int age){
        //springmvc可自动转换
        System.out.println("username:"+username+",age:"+age);
    }

    @RequestMapping("hello12")
    @ResponseBody
    public void save12(User user){

        System.out.println(user);
    }

    @RequestMapping("hello13")
    @ResponseBody
    public void save13(String [] str){
        System.out.println(str);
        System.out.println(Arrays.toString(str));
    }

    @RequestMapping("hello14")
    @ResponseBody
    public void save14(VO vo){
        System.out.println(vo.getUserList().get(0).toString());
        System.out.println(vo.getUserList().get(1).toString());
    }

    /**
     * 用@RequestBody，ajax请求
     *  contentType:"application/json;charset=utf-8"
     *
     * @param userList
     */
    @RequestMapping("hello15")
    @ResponseBody
    public void save15(@RequestBody List<User> userList){
        System.out.println(userList);
    }


    /**
     * @RequestParam
     * 当请求参数与方法接收参数不一致时，可以用注解指定名称
     * 当注解中参数只有一个时，可以省略value=
     * require:是否必须，如果指定为必须参数，如请求没有该参数报错
     * defaultValue：当没有请求参数时指定默认值
     * @param username
     */
    @RequestMapping("hello16")
    @ResponseBody
    public void save16(@RequestParam(value = "name",required = true,defaultValue = "ceshi")String username){
        System.out.println(username);
    }

    /**
     * Restful风格
     * GET:获取资源   /user/1  得到id =1 的user
     * POST:新建资源  /user/1   新增user
     * PUT:更新资源   /user/1  更新id =1 的user
     * DELETE:删除资源 /user/1  删除id =1 的user
     *
     * @RquestMapping("/user/{id}")
     * 用@PathVariable(value = "id" ,required = true)
     */
    @RequestMapping(value = "/hello17/{username}",method = RequestMethod.GET)
    @ResponseBody
    public void save17(@PathVariable(value = "username") String username){

        System.out.println(username);
    }

    //自定义类型转换器 converter：主要用于日期转换，springmvc内部有类型转换，但日期自动转换格式多样， 2011/11/11 可用 2011-11-11不可用

    @RequestMapping(value = "/hello18")
    @ResponseBody
    public void save18(Date date){

        System.out.println(date);
    }

    //获取请求头  @RequestHeader  :value 请求头名称,required 请求头是否必须

    @RequestMapping(value = "/hello20")
    @ResponseBody
    public void save20(@RequestHeader(value = "User-Agent",required = false) String userAgent){
        System.out.println(userAgent);

    }

    //@CookieValue(value= "",required)
    @RequestMapping(value = "/hello21")
    @ResponseBody
    public void save21(@CookieValue(value = "JSESSIONID",required = false) String sessionId){
        System.out.println(sessionId);

    }

    /**
     * 单文件上传：服务端
     * 1、导入jar fileupload
     * 2、配置文件上传解析器
     * 3、编写文件上传代码
     */

    @RequestMapping("hello22")
    @ResponseBody
    public void save22(@RequestParam("filename") String fileName,@RequestParam("upload") MultipartFile uploadFile) throws IOException {
        System.out.println(fileName);
        System.out.println(uploadFile);

        //保存文件
        //1、获取上传文件的名称
        //uploadFile.getName();
        String filName = uploadFile.getOriginalFilename();
        //转移文件方法
        uploadFile.transferTo(new File("D:\\fileUpload\\"+filName));
    }

    //多文件上传

    @RequestMapping("hello23")
    @ResponseBody
    public void save23(@RequestParam("filename") String fileName,@RequestParam("upload") MultipartFile uploadFile,@RequestParam("upload2") MultipartFile uploadFile2) throws IOException {
        System.out.println(fileName);
        System.out.println(uploadFile);
        System.out.println(uploadFile2);

        //保存文件
        //1、获取上传文件的名称
        //uploadFile.getName();
        String filName = uploadFile.getOriginalFilename();
        String filName2 = uploadFile2.getOriginalFilename();
        //转移文件方法
        uploadFile.transferTo(new File("D:\\fileUpload\\"+filName));
        uploadFile2.transferTo(new File("D:\\fileUpload\\"+filName2));
    }


    @RequestMapping("hello24")
    @ResponseBody
    public  void save24(String filename, MultipartFile[] upload) throws IOException {
        System.out.println(filename);

        for (MultipartFile file :upload) {
            String filName = file.getOriginalFilename();
            file.transferTo(new File("D:\\fileUpload\\"+filName));
        }

    }

}
