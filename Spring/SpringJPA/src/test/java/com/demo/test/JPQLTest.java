package com.demo.test;

import com.demo.dao.CustomerDao;
import com.demo.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JPQLTest {

     @Autowired
     private CustomerDao customerDao;

      @Test
      public void getCustomerByName(){
          Customer customer = customerDao.getJpa("客户2");
          System.out.println(customer);
      }

      @Test
      public void getByNameAndId(){
          Customer customer = customerDao.findCustomByNameAndId(3,"客户2");
          System.out.println(customer);
      }

    /**
     * 报错：Executing an update/delete query
     * 需要添加事务支持
     * springdatajpa使用jpql完成更新或删除操作
     * 需要手动配置支持事务：添加  @Transactional注解
     * 该注解默认执行成功后回滚事务
     * 需要使用@Rollback:false 配置不回滚事务
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void updateTest(){
            customerDao.updateCustomer(2,"客户111");
      }


}
