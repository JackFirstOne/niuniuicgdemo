package com.demo.dao;

import com.demo.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 * 只需要编写接口，不需要实现类
 * 接口需要继承接口 JpaRepository 和  JpaSpecificationExecutor
 * 实现泛型JpaRepository<操作实体类类型，实体类主键属性类型>  JpaSpecificationExecutor<操作实体类类型>
 */
public interface CustomerDao extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {

    /**
     * 使用jpql格式查询
     * 根据客户名称查询客户
     * jpql:from Customer where custName =?
     * 使用Query注解
     */
    @Query(value = "from Customer where custName = ?1")
    public Customer getJpa(String custName);

    /**
     * jpql查询：多参数查询
     * 根据客户名称和客户Id查询客户
     * jqpl: from Customer where custName = ? and custId = ?
     * 注意：默认情况下，有多个参数时，参数的顺序需要与jpql语句占位符一致
     * 可以修改占位符的取值来源 ？1，1为方法参数的位置
     */
    @Query(value = "from Customer where custName = ?2 and custId = ?1")
    public Customer findCustomByNameAndId(Integer id,String name);

    /**
     * jpql更新
     * 根据Id修改客户名称
     * sql: update cst_customer set cust_name = ? where cust_id = ?
     * jpql:update Customer set custName = ? where custId = ?
     *
     * @Query：仅代表查询，声明此方法是更新操作 需要使用 @Modifying
     */
    @Query(value = "update Customer set custName = ?2 where custId = ?1")
    @Modifying
    public void updateCustomer(Integer id,String name);


    /**
     * sql语句查询
     * 使用 @Query注解
     *    value="":sql语句
     *    nativeQuery = true :表示支持本地查询即为sql查询
     *                       若为false为jpql查询（默认为false）
     */
    @Query(value = "select * from cst_customer",nativeQuery = true)
    public List<Object []> findAllBySql();

    /**
     * sql查询
     * 根据条件查询
     */
    @Query(value = "select * from cst_customer where cust_name like ?1",nativeQuery = true)
    public List<Object [] > findOneBySql(String name);


    /**
     * 方法命名规则查询
     * 方法规则约定：
     *      1、findBy+对象中的属性名称:根据某属性查询
     *                对象中的属性名称字母大写，查询的条件
     *   findBy->from xxx(实体类)
     *   CustName - >where  custName = ?
     *   findByCustName:根据客户名称查询
     */

    public Customer findByCustName(String name);

    /**
     * findBy+属性名称+查询方式
     * 查询方式： like | isnull
     * findByCustNameLike
     */

    public List<Customer> findByCustNameLike(String name);


    /**
     * 多条件查询
     *  findBy+属性名称+查询方式+多条件的连接符（and|or）+属性名称+查询方式（精准匹配可以省略）
     *   根据客户名称模糊查询并且根据行业精准匹配
     *   主要方法参数顺序必须与方法名称顺序一致
     */
    public Customer findByCustNameLikeAndCustIndustry(String name,String industry);
}
