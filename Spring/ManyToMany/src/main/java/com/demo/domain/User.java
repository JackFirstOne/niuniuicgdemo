package com.demo.domain;

import org.hibernate.annotations.Cascade;
import org.springframework.test.annotation.Rollback;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_user")
public class User {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "user_id")
       private Long userId;
       @Column(name = "user_name")
       private String username;
       @Column(name = "user_age")
       private Long age;

       /**
        *
        * 配置用户到角色的多对多关系
        * 1、声明表关系
        *     @ManyToMany:多对多
        *       targetEntity：目标对象
        * 2、配置中间表（包含两个外键）
        * 配置级联  @ManyToMany cascade
        */
       @ManyToMany(targetEntity = Role.class,cascade = CascadeType.ALL)
       @JoinTable(name = "sys_user_role", //生成关联表名称
                  //当前对象在中间表的外键，生成关联表外键字段 name:中间表字段名称，referencedColumnName：参照主表主键名
                  joinColumns = {@JoinColumn(name = "sys_user_id",referencedColumnName = "user_id")},
                   //对方对象在中间表的外键
                  inverseJoinColumns = {@JoinColumn(name = "sys_role_id",referencedColumnName = "role_id")})
       private Set<Role> roles = new HashSet<Role>();

       public Set<Role> getRoles() {
              return roles;
       }

       public void setRoles(Set<Role> roles) {
              this.roles = roles;
       }

       public Long getUserId() {
              return userId;
       }

       public void setUserId(Long userId) {
              this.userId = userId;
       }

       public String getUsername() {
              return username;
       }

       public void setUsername(String username) {
              this.username = username;
       }

       public Long getAge() {
              return age;
       }

       public void setAge(Long age) {
              this.age = age;
       }

       @Override
       public String toString() {
              return "User{" +
                      "userId=" + userId +
                      ", username='" + username + '\'' +
                      ", age=" + age +
                      '}';
       }
}
