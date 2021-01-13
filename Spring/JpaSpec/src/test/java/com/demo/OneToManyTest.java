package com.demo;

import com.demo.dao.CustomerDao;
import com.demo.dao.LinkManDao;
import com.demo.domain.Customer;
import com.demo.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class OneToManyTest {

      @Autowired
      private CustomerDao customerDao;
      @Autowired
      private LinkManDao linkManDao;

      @Test
      @Transactional
      @Rollback(value = false)
      public void test1(){
            Customer customer = new Customer();
            customer.setCustName("京东");

            LinkMan linkMan = new LinkMan();
            linkMan.setLinkManName("黎明");

            //创建关联：从主表角度创建关系
            customer.getLinMans().add(linkMan);

            customerDao.save(customer);
            linkManDao.save(linkMan);
      }


      @Test
      @Transactional
      @Rollback(value = false)
      public void test2(){
            Customer customer = new Customer();
            customer.setCustName("京东");

            LinkMan linkMan = new LinkMan();
            linkMan.setLinkManName("黎明");

            //创建关联 :从联系人角度添加关系
            linkMan.setCustomer(customer);

            customerDao.save(customer);
            linkManDao.save(linkMan);
      }


      /**
       * 双方配置关系
       * 会多一条修改语句                                                mappedBy为从表配置的影射主表类的名称
       * 主表放弃维护权：在 private Set<LinkMan> linkMan 只添加@OneToMany(mappedBy="customer")
       */
      @Test
      @Transactional
      @Rollback(value = false)
      public void test3(){
            Customer customer = new Customer();
            customer.setCustName("京东");

            LinkMan linkMan = new LinkMan();
            linkMan.setLinkManName("黎明");

            //创建关联 :从从表角度添加关系
            linkMan.setCustomer(customer);
            //创建关联 :从主表角度添加关系
            customer.getLinMans().add(linkMan);

            customerDao.save(customer);
            linkManDao.save(linkMan);
      }

      /**
       *
       * CascadeAdd:级联添加
       * 保存一个客户同时保存该客户的所有联系人
       */
      @Test
      @Transactional
      @Rollback(value = false)
      public void test4(){

            Customer customer = new Customer();
            customer.setCustName("百度");

            LinkMan linkMan = new LinkMan();
            linkMan.setLinkManName("黎明");

            //创建关联 :从从表角度添加关系
            linkMan.setCustomer(customer);
            //创建关联 :从主表角度添加关系
            customer.getLinMans().add(linkMan);

            customerDao.save(customer);
            linkManDao.save(linkMan);

      }

      /**
       * 删除数据
       *        * 若主表放弃维护权，则不能完成删除操作，如要删除数据，需要进行级联删除
       *  级联删除
       *  需要修改配置文件 create改为 update
       */
      @Test
      @Transactional
      @Rollback(value = false)
      public void test5(){
            Customer one = customerDao.findOne(1);
            customerDao.delete(one);
      }
}
