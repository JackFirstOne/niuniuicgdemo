package com.demo.pojo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.util.Date;

/**
 * 在编译时会根据注解自动生成对应的方法
 * @Data包含 getter/setter/equals/toString/hasCode
 * @Slf4j 自动在类中提供log变量，用的是slf4j的日志功能
 */
@Data
@Table(name = "tb_user")
public class User {

       /**
        * @Colum 数据库命名与类属性名有规则对应，可以不加该注解，可以自动映射
        */
       @Id
       @KeySql(useGeneratedKeys = true) //主键回填，新增id为空，数据库生产
       private Integer id;
       private String  userName;
       private String  password;
       private String name;
       private Integer age;
       private Integer sex;
       private Date birthday;
       private String note;
       private Date created;
       private Date updated;

}
