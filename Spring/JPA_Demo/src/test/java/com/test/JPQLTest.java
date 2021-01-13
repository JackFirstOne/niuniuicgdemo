package com.test;

import com.demo.domain.Customer;
import com.demo.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class JPQLTest {

    /**
     * 查询全部
     * jpql: from com.demo.domain.Customer
     * sql:select * from cst_customer
     */
    @Test
      public void  selAll(){

        //1、获取entityManage对象
        EntityManager entityManage = JpaUtils.getEntityManage();
        EntityTransaction transaction = entityManage.getTransaction();
        transaction.begin();

        //String jpql = "from com.demo.domain.Customer";
        String jpql = "from Customer ";
        Query query = entityManage.createQuery(jpql);//创建query查询对象
        
        //发送查询并封装结果集
        List resultList = query.getResultList();
        for (Object cu :
                resultList) {

            System.out.println(cu);
        }
        transaction.commit();
        entityManage.close();
    }


    /**
     * 排序查询
     * 倒序查询
     */
    @Test
    public void selOrder(){

        EntityManager entityManage = JpaUtils.getEntityManage();
        EntityTransaction transaction = entityManage.getTransaction();

        transaction.begin();

        //定义jpql查询语句
        String jpql = "from Customer order by custId desc";
        //根据jpql获取查询对象
        Query query = entityManage.createQuery(jpql);
        //根据查询语句获取查询结果
        List resultList = query.getResultList();
        for (Object customer:
             resultList) {
            System.out.println(customer);
        }

        transaction.commit();
        entityManage.close();
    }

    /**
     * 查询总条数
     */
    @Test
     public void  getContent(){
         EntityManager entityManage = JpaUtils.getEntityManage();
         EntityTransaction transaction = entityManage.getTransaction();
         transaction.begin();

         String jpql = "select count(custId) from Customer ";
         Query query = entityManage.createQuery(jpql);

         Object singleResult = query.getSingleResult();
         int count = Integer.parseInt(singleResult.toString());

         transaction.commit();
         entityManage.close();

     }


    /**
     * 分页查询
     *  sql:select * from cst_custom limit ?,?
     */
    @Test
    public void limitSel(){

        EntityManager entityManage = JpaUtils.getEntityManage();
        EntityTransaction transaction = entityManage.getTransaction();
        transaction.begin();

        String jpql="from Customer";
        Query query = entityManage.createQuery(jpql);

        //对参数赋值：开始页数，每页条数
        query.setFirstResult(0);
        query.setMaxResults(2);

        List resultList = query.getResultList();

        for (Object custom:
             resultList) {
            System.out.println(custom);
        }

        transaction.commit();
        entityManage.close();
    }

    /**
     * 条件查询
     * sql:select * from cst_custom where cust_name like ?
     * jpql: from Customer where custName like ?
     */
    @Test
    public void selBy(){

        EntityManager entityManage = JpaUtils.getEntityManage();
        EntityTransaction transaction = entityManage.getTransaction();
        transaction.begin();

        String jpql = "from Customer where custName like ?";
        Query query = entityManage.createQuery(jpql);
       //设置占位符赋值：第一个参数 占位符的位置，第二个参数 参数值
        query.setParameter(1,"%3%");

        List resultList = query.getResultList();


        for (Object customer:
                resultList) {
            System.out.println( customer);
        }

        transaction.commit();
        entityManage.close();
    }
}
