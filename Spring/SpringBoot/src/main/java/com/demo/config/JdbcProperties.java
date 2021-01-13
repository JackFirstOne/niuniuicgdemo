package com.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 从配置文件application.properties中读取配置项到对象中
 * prefix:配置项前缀
 * 配置项类中的类变量名必须与前缀之后的配置项名称保持松散绑定
 *
 *
 */
//@ConfigurationProperties(prefix = "jdbc")
public class JdbcProperties {

       private String dirverClassName;
       private String url;
       private String username;
       private String password;

    public String getDirverClassName() {
        return dirverClassName;
    }

    public void setDirverClassName(String dirverClassName) {
        this.dirverClassName = dirverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
