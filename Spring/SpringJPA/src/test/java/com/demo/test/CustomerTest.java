package com.demo.test;

import com.demo.dao.CustomerDao;
import com.demo.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//配置spring单元测试
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CustomerTest {

       @Autowired
       private CustomerDao customerDao;

       @Test
       public void test1(){
           Customer customer = customerDao.findOne(3);
           System.out.println(customer);
       }

    /**
     * save():保存或者更新方法
     * 根据传入对象是否有Id
     * 如果没有Id,添加操作
     * 如果有Id,修改操作（根据Id查询，更新）：更新所有字段，属性不设值更新为空
     */
       @Test
       public void testSave(){
            Customer customer = new Customer();
            customer.setCustId(5);
            customer.setCustName("我是客户123");
            customerDao.save(customer);
       }


       @Test
       public void delTest(){

           customerDao.delete(8);
       }

    /**
     * 查询所有
     */
    @Test
    public void testFindAll(){
        List<Customer> all = customerDao.findAll();
        for (Customer o:
             all) {
            System.out.println(o);
        }
    }

    /**
     * 测试统计查询
     */
    @Test
    public void testCount(){
        long count = customerDao.count();
        System.out.println(count);
    }

    /**
     * 是否存在
     */
    @Test
    public void testExit(){
        boolean exists = customerDao.exists(10);
        if (exists){
            System.out.println("用户存在");
        }else{
            System.out.println("用户不存在");
        }
    }

    /**
     * 根据Id查询
     * getOne(id)需要使用 @Transactional 否则报错，无法初始化代理，no session
     * getOne(id) ：延迟加载
     * findOne(id): 立即加载
     */
    @Test
    @Transactional
    public void getOneTest(){
        Customer one = customerDao.getOne(2);
        //使用getOne：是懒加载模式，在获取时是代理对象，在使用时生成真正的对象
        System.out.println(one);
    }

    //jpql查询
}
