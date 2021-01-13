package com.demo.domain;

import javax.persistence.*;
import java.io.Serializable;
import com.demo.domain.Customer;
@Entity
@Table(name = "cst_linkman")
public class LinkMan implements Serializable {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "lkm_id")
       private Integer linkManId;
       @Column(name = "lkm_name")
       private String linkManName;
       @Column(name = "lkm_gender")
       private String linkManGender;
       @Column(name = "lkm_phone")
       private String linkManPhone;
       @Column(name = "lkm_mobile")
       private String linkManMoblie;
       @Column(name = "lkm_email")
       private String linkManEmail;
       @Column(name = "lkm_position")
       private String linkManPosition;
       @Column(name = "lkm_memo")
       private String linkManMemo;

       //配置多对一关系

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "lkm_cust_id",referencedColumnName = "cust_id")
    private Customer customer;


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getLinkManId() {
        return linkManId;
    }

    public void setLinkManId(Integer linkManId) {
        this.linkManId = linkManId;
    }

    public String getLinkManName() {
        return linkManName;
    }

    public void setLinkManName(String linkManName) {
        this.linkManName = linkManName;
    }

    public String getLinkManGender() {
        return linkManGender;
    }

    public void setLinkManGender(String linkManGender) {
        this.linkManGender = linkManGender;
    }

    public String getLinkManPhone() {
        return linkManPhone;
    }

    public void setLinkManPhone(String linkManPhone) {
        this.linkManPhone = linkManPhone;
    }

    public String getLinkManMoblie() {
        return linkManMoblie;
    }

    public void setLinkManMoblie(String linkManMoblie) {
        this.linkManMoblie = linkManMoblie;
    }

    public String getLinkManEmail() {
        return linkManEmail;
    }

    public void setLinkManEmail(String linkManEmail) {
        this.linkManEmail = linkManEmail;
    }

    public String getLinkManPosition() {
        return linkManPosition;
    }

    public void setLinkManPosition(String linkManPosition) {
        this.linkManPosition = linkManPosition;
    }

    public String getLinkManMemo() {
        return linkManMemo;
    }

    public void setLinkManMemo(String linkManMemo) {
        this.linkManMemo = linkManMemo;
    }


    @Override
    public String toString() {
        return "LinkMan{" +
                "linkManId=" + linkManId +
                ", linkManName='" + linkManName + '\'' +
                ", linkManGender='" + linkManGender + '\'' +
                ", linkManPhone='" + linkManPhone + '\'' +
                ", linkManMoblie='" + linkManMoblie + '\'' +
                ", linkManEmail='" + linkManEmail + '\'' +
                ", linkManPosition='" + linkManPosition + '\'' +
                ", linkManMemo='" + linkManMemo + '\'' +

                '}';
    }
}
