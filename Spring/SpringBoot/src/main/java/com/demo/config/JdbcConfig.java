package com.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 *  @Value:将配置文件中的配置配置项读取到属性中
 * @ConfigurationProperties:将配置文件（默认必须为applicationContext.properties/.yml）中的配置项读取到一个对象中
 */
//@Configuration //指定该文件为配置文件
//@PropertySource("classpath:jdbc.properties")         //读取配置文件
//@EnableConfigurationProperties(JdbcProperties.class)  //读取java配置文件类
public class JdbcConfig {
    /*第一种方法：使用 @PropertySource*/
      /*  @Value("${jdbc.driverClassName}") //读取配置文件配置项值
        String driver;
        @Value("${jdbc.url}")
        String url;
        @Value("${jdbc.username}")
        String username;
        @Value("${jdbc.password}")
        String password;

        @Bean
        public DataSource getDataSource(){
            DruidDataSource druidDataSource = new DruidDataSource();
            druidDataSource.setDriverClassName(driver);
            druidDataSource.setUrl(url);
            druidDataSource.setUsername(username);
            druidDataSource.setPassword(password);
            return  druidDataSource;
        }*/
    /*第二中方法：使用 @EnableConfigurationProperties 配置类使用@ConfigurationProperties(prefix = "jdbc")*/
   /* @Bean
    public DataSource getDataSource(JdbcProperties jdbcProperties){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(jdbcProperties.getDirverClassName());
        druidDataSource.setUrl(jdbcProperties.getUrl());
        druidDataSource.setUsername(jdbcProperties.getUsername());
        druidDataSource.setPassword(jdbcProperties.getPassword());
        return  druidDataSource;
    }*/

    /*第三中方法：配置方法使用@ConfigurationProperties(prefix = "jdbc")*/
   /* @ConfigurationProperties(prefix = "jdbc")
    @Bean
    public DataSource getDataSource(){
          return  new DruidDataSource();
    }*/

}
