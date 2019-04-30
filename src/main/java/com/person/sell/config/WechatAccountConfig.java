package com.person.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信账号相关配置
 *
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    private String mpAppId;     //公众平台id
    private String mpAppSecret; //公众平台密钥
    /*
        (微信扫描二维码登录)开放平台暂无相关账号信息，目前只是先配置
     */
    private String openAppId;   //开放平台id
    private String openAppSecret;   //开放平台密钥
    /*
        微信支付暂无相关账号信息，目前只是先配置
     */
    private String mchId;   //商户号
    private String mchKey;  //商户密钥
    private String keyPath; //商户证书路径
    private String notifyUrl; //微信支付异步通知地址
}
