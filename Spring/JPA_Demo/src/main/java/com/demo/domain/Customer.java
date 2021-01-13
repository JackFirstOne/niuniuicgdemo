package com.demo.domain;

import javax.persistence.*;

/**
 * 客户实体类
 * 配置映射关系：
 *    1、实体类和数据表的关系
 *    2、实体类中属性和表中字段的关系
 * 1、使用注解：@Entity  声明实体类
 *            @Table   配置实体类和表的映射关系
 *                     name:配置映射表的名称
 * 2、        @Id                配置声明主键
 *            @GeneratedValue   配置主键生成策略
 *                   strategy   自增方式
 *                           IDENTITY  自增
 *                           SEQUENCE  序列  orcal支持（不支持自增），mysql不支持
 *                           TABLE     JPA提供的机制，通过数据库表的形式生成自增主键
 *                           AUTO      由程序自动生成主键
 *            @Column      配置实体类属性和表中字段的关系
 */
@Entity
@Table(name = "cst_customer")
public class Customer {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "cust_id")
       private Integer custId;

       @Column(name = "cust_name")
       private String custName;

       @Column(name = "cust_source")
       private String custSource;

       @Column(name = "cust_level")
       private String custLevel;

       @Column(name = "cust_industry")
       private String custIndustry;

       @Column(name = "cust_address")
       private String custAddress;

       @Column(name = "cust_phone")
       private String custPhone;

    public Customer() {
    }

    public Customer(Integer custId, String custName, String custSource, String custLevel, String custIndustry, String custAddress, String custPhone) {
        this.custId = custId;
        this.custName = custName;
        this.custSource = custSource;
        this.custLevel = custLevel;
        this.custIndustry = custIndustry;
        this.custAddress = custAddress;
        this.custPhone = custPhone;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custPhone='" + custPhone + '\'' +
                '}';
    }
}
