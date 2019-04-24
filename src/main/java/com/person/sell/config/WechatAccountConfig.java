package com.person.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信账号相关配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    private String mpAppId;
    private String mpAppSecret;
    private String mchId;   //商户号
    private String mchKey;  //商户密钥
    private String keyPath; //商户证书路径
    private String notifyUrl; //微信支付异步通知地址
}
