package com.demo.test;

import com.demo.dao.CustomerDao;
import com.demo.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MeathTest {

      @Autowired
      private CustomerDao customerDao;

      @Test
      public void testFindBy(){
          Customer customer = customerDao.findByCustName("客户2");
          System.out.println(customer);
      }

    @Test
    public void testFindByLike(){
        List<Customer> customers = customerDao.findByCustNameLike("客户%");
        for (Customer customer:customers) {
            System.out.println(customer);
        }

    }


    @Test
    public void testFindByLikeAnd(){
        Customer customer = customerDao.findByCustNameLikeAndCustIndustry("客户%","ces");
        System.out.println(customer);
    }
}
