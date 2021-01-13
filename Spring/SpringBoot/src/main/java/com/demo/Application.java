package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Springboot工程都有一个启动引导类，这是工程的入口类
 * 需要添加注解 @SpringBootApplication
 * 包含多个注解，
 * 声明配置文件，自动加载其他配置文件，扫描当前所在包及子包
 *
 *springboot:
 * 常用注解：
 * @Configuration:声明一个类为配置类，代替xml
 * @Bean:声明在方法上，将方法的返回值注册一个bean
 *
 */
@SpringBootApplication
//@MapperScan("com.demo.mapper") //配置扫描mybatis所有业务mapper接口，指定接口所在包路径，配置后mapper类可不加@Mapper注解
@MapperScan("com.demo.mapper") //注意包使用tk.mybatis ,上面注解是mybatis注解
public class Application {

    public static void main(String[] args) {
        //传参入口类及参数
        SpringApplication.run(Application.class,args);
    }
}
