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

    //微信开放平台url
    public String WechatOpenAuthorize;

    //系统url
    public String sell;
}
