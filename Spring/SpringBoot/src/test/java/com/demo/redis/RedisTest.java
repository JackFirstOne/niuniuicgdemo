package com.demo.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

      @Autowired
      private RedisTemplate redisTemplate;

      @Test
      public void test1(){

          //string字符串
          redisTemplate.opsForValue().set("str","heima");
          //redisTemplate.boundValueOps("str").set("heima");
          System.out.println(redisTemplate.opsForValue().get("str"));


          //hash 散列
          redisTemplate.boundHashOps("b_key").put("name","heima");
          redisTemplate.boundHashOps("b_key").put("age",18);

          Set b_key = redisTemplate.boundHashOps("b_key").keys();
          System.out.println("hash散列的所有域"+b_key);
          List b_value = redisTemplate.boundHashOps("b_key").values();
          System.out.println("hash散列的所有值"+b_value);


          //set集合
          redisTemplate.boundSetOps("s_key").add("a","b","c");
          Set s_key = redisTemplate.boundSetOps("s_key").members();
          System.out.println("set集合中的所有元素："+s_key);


          //list 列表
          redisTemplate.boundListOps("key").leftPush("e");
          redisTemplate.boundListOps("key").leftPush("b");
          redisTemplate.boundListOps("key").leftPush("a");
          //获取全部元素
          List key = redisTemplate.boundListOps("key").range(0, -1);
          System.out.println("list列表中所有元素"+key);


          //sorted set 有序集合
          redisTemplate.boundSetOps("z_key").add("a",30);
          redisTemplate.boundSetOps("z_key").add("b",20);
          redisTemplate.boundSetOps("z_key").add("c",10);
          List z_key = redisTemplate.boundListOps("z_key").range(0, -1);
          System.out.println("sorted set 有序集合中的所有元素："+z_key);


      }

}
