package com.person.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * reidis 分布式锁，用于当多个线程访问同一数据时保证操作时的互斥
 * Class is not used
 */
@Component
@Slf4j
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 加锁，通过 redis 命令 setnx 和 getset 特性实现，保证只有一个线程拿到锁
     * 设置超时时间，判断该 key 是否过期
     * 若未过期说明数据正在操作中，通过逻辑操作阻止其它线程访问数据，达到加锁效果
     * @param key 字符串的key
     * @param value 保存的值，当前时间+超时时间
     * @return true/false
     */
    public boolean lock(String key,String value){

        //redis 命令：setnx  若key存在不做操作，key 不存在相当于 set 命令
        //若 key 不存在则设值
        if(redisTemplate.opsForValue().setIfAbsent(key,value)){
            return true;
        }

        //key 存在，获取value
        String currentValue = redisTemplate.opsForValue().get(key);
        //若 key 过期超时
        if(!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()){
            //获取上一个锁的时间,并设置传递的时间参数，即 redis 命令：getset
            String oldValue = redisTemplate.opsForValue().getAndSet(key,value);
            if(!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)){
                return true;
            }
        }
        return false;
    }

    /**
     * 解锁
     * @param key
     * @param value
     */
    public void unlock(String key,String value){
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            //通过判断传递的超时时间和 key 的超时时间是否相同来判断是否为拥有锁的线程
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                //解锁即删除 redis 字符串
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        }catch (Exception e){
            log.error("【redis分布式锁】解锁异常，{}",e);
        }
    }



}
