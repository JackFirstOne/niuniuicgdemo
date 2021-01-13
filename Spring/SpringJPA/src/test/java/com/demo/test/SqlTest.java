package com.demo.test;

import com.demo.dao.CustomerDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SqlTest {

      @Autowired
      private CustomerDao customerDao;

      @Test
      public void selAllBySql(){
          List<Object[]> allBySql = customerDao.findAllBySql();
          for (Object [] customer:allBySql) {
              System.out.println(Arrays.toString(customer));
          }
      }

    @Test
    public void selOneBySql(){
        List<Object[]> allBySql = customerDao.findOneBySql("客户%");
        for (Object [] customer:allBySql) {
            System.out.println(Arrays.toString(customer));
        }
    }


}
