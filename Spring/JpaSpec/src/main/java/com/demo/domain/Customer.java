package com.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 实体类
 *
 */
@Entity
@Table(name = "cst_customer")
public class Customer implements Serializable {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "cust_id")
       private Integer custId;
       @Column(name="cust_name")
       private String custName;
       @Column(name = "cust_address")
       private String custAddress;
       @Column(name = "cust_level")
       private String custLevel;
       @Column(name ="cust_phone")
       private String custPhone;
       @Column(name = "cust_source")
       private String custSource;
       @Column(name = "cust_industry")
       private String custIndustry;

       //配置客户和联系人之间的关系
       //@OneToMany(targetEntity = LinkMan.class)
       //配置外键,name:外键字段名称  referencedColumnName:参照的主表主键字段
       //@JoinColumn(name = "lkm_cust_id",referencedColumnName = "cust_id")
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)//cascade:配置级联操作
    private Set<LinkMan> linMans = new HashSet<LinkMan>();


    public Set<LinkMan> getLinMans() {
        return linMans;
    }

    public void setLinMans(Set<LinkMan> linMans) {
        this.linMans = linMans;
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

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                '}';
    }
}
