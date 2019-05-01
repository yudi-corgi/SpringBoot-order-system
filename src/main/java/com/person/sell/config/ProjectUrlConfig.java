package com.person.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "projecturl")
@Component
public class ProjectUrlConfig {

    //微信公众账号授权url
    public String wechatMpAuthorize;

    //用户登录url
    public String userLoginUrl;

    //系统url
    public String sell;
}
