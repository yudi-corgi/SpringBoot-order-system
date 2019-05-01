package com.person.sell.constant;

/**
 * Redis 常量
 */
public interface RedisConstant {

    String TOKEN_PREFIX = "token_%s"; //%s 是格式化字符串时会转换成文本

    Integer EXPIRE = 7200; //2小时


}
