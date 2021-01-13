package com.test;

import com.demo.domain.Customer;
import com.demo.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPATest {


    /**
     * 新增操作
     * jpa操作步骤：
     *    1、加载配置文件创建工厂（实体类管理工厂）对象  使用Persistence 静态方法 createEntityManagerFactory
     *
     *    2、通过实体类管理工厂创建实体类管理器
     *    EntityManagerFactory 创建过程比较浪费资源（耗时），对象线程安全
     *    创建一个公共的EntityManagerFactory对象，解决耗时问题：
     *    可以使用静态代码块的方式
     *
     *    3、获取事务对象，开启事务
     *    EntityManager：与数据库交互对象，
     *                  beginTransaction()
     *                  presist()  保存数据
     *                  remove()   删除数据
     *                  marge()    更新数据
     *                  find/getRefrence:根据ID查询
     *    EntityTransaction：事务对象
     *                    begin() 开启事务
     *                    commit() 提交事务
     *                    rollback() 回滚事务
     *    4、进行增删改查操作
     *
     *    5、提交事务（回滚事务）
     *    6、释放资源
     */
    @Test
    public void addCustomer(){


       /* EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();*/
        EntityManager entityManager = JpaUtils.getEntityManage();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();//开启事务

        Customer customer = new Customer();
        customer.setCustName("客户2");
        customer.setCustAddress("北京丰台");
        customer.setCustPhone("1999999999");

        entityManager.persist(customer);//保存操作
        transaction.commit();

        entityManager.close();
        //entityManagerFactory.close();


    }

    /**
     * 根据Id查询用户
     * 需要修改配置文件为update
     *
     * find：立即加载，在获取数据的时候加载对象
     */
    @Test
    public void selCustomter(){
        EntityManager entityManage = JpaUtils.getEntityManage();
        EntityTransaction transaction = entityManage.getTransaction();
        transaction.begin();

        //根据Id查询数据 两个参数，第一个需要查询的对象字节码，第二个参数查询的主键取值
        Customer customer = entityManage.find(Customer.class, 1);
        System.out.println(customer);

        transaction.commit();
        entityManage.close();


    }

    /**
     * 根据Id查询用户
     * 需要修改配置文件为update
     *
     * getReference:延迟加载，获取动态代理对象，在用的时候才会加载对象
     */
    @Test
    public void selCustomter2(){

        EntityManager entityManage = JpaUtils.getEntityManage();
        EntityTransaction transaction = entityManage.getTransaction();
        transaction.begin();

        //根据Id查询数据 两个参数，第一个需要查询的对象字节码，第二个参数查询的主键取值
        Customer customer = entityManage.getReference(Customer.class,1);
        System.out.println(customer);

        transaction.commit();
        entityManage.close();


    }


     @Test
     public void delCustomerById(){

         EntityManager entityManage = JpaUtils.getEntityManage();
         EntityTransaction transaction = entityManage.getTransaction();

         transaction.begin();

         //根据Id查询客户
         Customer customer = entityManage.find(Customer.class, 1);

         //将查询出的客户删除
         entityManage.remove(customer);

         transaction.commit();
         entityManage.close();

     }


    /**
     * 修改操作
     * 调用 merge 更新
     */
     @Test
     public void updateCustomer(){

         EntityManager entityManage = JpaUtils.getEntityManage();
         EntityTransaction transaction = entityManage.getTransaction();

         transaction.begin();

         Customer customer = entityManage.find(Customer.class, 2);
         customer.setCustAddress("我是修改后的地址");
         entityManage.merge(customer);

         transaction.commit();
         entityManage.close();
     }

     //jpql:jpa的查询语句  java persister query lanager :查询对象及属性，不查询数据库表及字段
    //与sql语句语法相似



}
