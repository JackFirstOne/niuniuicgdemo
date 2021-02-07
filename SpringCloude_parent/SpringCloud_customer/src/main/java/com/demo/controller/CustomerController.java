package com.demo.controller;

import com.demo.pojo.User;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/customer")
@Slf4j
@DefaultProperties(defaultFallback = "defaultFallBack")
public class CustomerController {

        @Autowired
        private RestTemplate restTemplate;

        @Autowired
        private DiscoveryClient discoveryClient;

        @RequestMapping("/{id}")
        //@HystrixCommand(fallbackMethod = "queryByIdFallback")  //访问异常熔断处理方法
        @HystrixCommand
        public String getUserById(@PathVariable Integer id){
            //String url="http://localhost:9090/user/8";

            //获取eureka中的实例
           /* List<ServiceInstance> instances = discoveryClient.getInstances("user-service");

            ServiceInstance serviceInstance = instances.get(0);
            String host = serviceInstance.getHost();
            int port = serviceInstance.getPort();
            String url="http://"+host+":"+port+"/user/"+id;
            System.out.println("---------"+url);
            User forObject = restTemplate.getForObject(url, User.class);*/

            String url="http://user-service/user/"+id;
            return  restTemplate.getForObject(url, String.class);
        }


        public String queryByIdFallback(Integer id){
              log.error("查询用户信息失败。id:{}",id);
              return "网络拥挤";
        }


        public String defaultFallBack(){
            return "默认提示：网络拥挤";
        }
}
