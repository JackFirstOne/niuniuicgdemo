package com.demo;

import com.demo.dao.CustomerDao;
import com.demo.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpecTest {

      @Autowired
      private CustomerDao customerDao;

      /**
       * 根据条件查询单个对象
       *
       *
       *
       */
      @Test
      public void test1(){

            /**
             * 匿名内部类
             *  root :获取属性
             *  criteriaBuilder：获取查询方式
             */
            Specification<Customer> specification = new Specification<Customer>() {
                  public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                        //获取查询条件
                        Path<Object> custName = root.get("custName");
                        //2、构造查询条件:精准匹配 .equal()
                        //第一个参数，需要比较的属性，第二个参数，查询条件值
                        Predicate predicate = criteriaBuilder.equal(custName, "客户2");
                        return predicate;
                  }
            };
            Customer one = customerDao.findOne(specification);
            System.out.println(one);
      }


      /**
       * 动态查询：多条件查询
       *    案例：根据客户名称和客户所属行业查询
       */
      @Test
      public void test2(){
            /**
             * 根据客户名称和所属行业查询
             * 1、构造客户名称精准查询
             * 2、构造所属行业精准查询
             * 3、将上述查询关联
             */
            Specification<Customer> specification = new Specification<Customer>() {
                    public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                          Path<Object> custName = root.get("custName");
                          Path<Object> custIndustry = root.get("custIndustry");

                          //构造查询
                          Predicate pName = criteriaBuilder.equal(custName, "客户");
                          Predicate pIndustry = criteriaBuilder.equal(custIndustry, "ces");
                          //将多个查询条件组合（满足条件一并且满足条件二||满足条件一或者满足条件二）
                          Predicate and = criteriaBuilder.and(pName, pIndustry);

                          return and;
                    }
              };

            Customer one = customerDao.findOne(specification);
            System.out.println(one);
      }

      /**
       * 根据客户名称模糊匹配，返回客户列表
       *
       * equal:根据root得到的path对象比较  custName
       * gt,lt,le,like:得到path对象，根据path指定比较的参数类型，再比较  custName.as(String.class)
       */
      @Test
      public void getCustomers(){
            Specification specification = new Specification() {
                  public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                        Path custName = root.get("custName");
                        Predicate like = criteriaBuilder.like(custName.as(String.class), "客户%");
                        return like;
                  }
            };
            List all = customerDao.findAll(specification);
            for (Object customer:all) {
                  System.out.println(customer);
            }
      }

      /**
       *    指定顺序查询:倒序查询
       */
      @Test
      public void getCustomers2(){
            Specification specification = new Specification() {
                  public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                        Path custName = root.get("custName");
                        Predicate like = criteriaBuilder.like(custName.as(String.class), "客户%");
                        return like;
                  }
            };
            //创建排序对象,需要构造，第一个参数：正序或倒序，第二个参数，排序的属性
            Sort sort = new Sort(Sort.Direction.DESC,"custId");
            List all = customerDao.findAll(specification, sort);
            for (Object customer:all) {
                  System.out.println(customer);

            }
      }

      /**
       * 分页查询
       */
       @Test
       public void testSpec(){
             Specification specification = new Specification() {
                   public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                         Path custName = root.get("custName");
                         Predicate like = criteriaBuilder.like(custName.as(String.class), "客户%");
                         return like;
                   }
             };
             //pageable：接口，pageRequest:pageable实现类，需要传参：第一个参数，当前页，第二个参数，总页数
             Pageable pageable = new PageRequest(0,2);
             Page all = customerDao.findAll(specification, pageable);
             System.out.println(all.getTotalElements());//获取总条数
             System.out.println("-------------------------------");
             System.out.println(all.getContent());//得到数据集合列表
             System.out.println("-------------------------------");
             System.out.println(all.getTotalPages());//获取总页数

       }

}
