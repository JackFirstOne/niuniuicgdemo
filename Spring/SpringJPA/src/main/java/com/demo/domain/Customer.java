package com.demo.domain;

import javax.persistence.*;

/**
 * 实体类
 *
 */
@Entity
@Table(name = "cst_customer")
public class Customer {

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
