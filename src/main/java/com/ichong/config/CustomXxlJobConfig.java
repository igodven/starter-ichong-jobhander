package com.ichong.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * ClassName: XxlJobConfig
 * Description:
 *
 * @author 陈高文
 * @date 2023/4/24 15:50
 */
@ConfigurationProperties(
        prefix = "xxl-job"
)
@Configuration
public class CustomXxlJobConfig implements Serializable {

    private String username;
    private String password;
    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



}
