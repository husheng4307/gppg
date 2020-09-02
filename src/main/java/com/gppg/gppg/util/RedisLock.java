package com.gppg.gppg.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Created by husheng
 * @on 20-5-3 下午6:08
 * @Version 1.0
 */
@Component
public class RedisLock {

    //redis中存任务锁的key前缀
    public final static String MOGUDING_TIMER_LOCK_KEY = "apt:lock:job:";

    @Autowired
    private RedisUtils redisUtil;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 判断是否有锁。有，返回true；否，返回false,设置一定期效的锁
     * @param lockName
     * @param expire  锁的有效时间长，单位：秒
     * @return
     */
    public boolean requireLock(String lockName,long expire){
        String key = MOGUDING_TIMER_LOCK_KEY+lockName;
        if (!redisUtil.hasKey(key)) {
            redisUtil.set(key,"1",expire);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 上锁
     * 将键值对设定一个指定的时间timeout.
     *
     * @param lockName
     * @param timeout 键值对缓存的时间，单位是秒
     * @return 设置成功返回true，否则返回false
     */
    public  boolean requireLock(String lockName, Object value, long timeout) {
        String key = MOGUDING_TIMER_LOCK_KEY+lockName;
        //底层原理就是Redis的setnx方法
        boolean isSuccess = redisTemplate.opsForValue().setIfAbsent(key, value , timeout , TimeUnit.SECONDS);
        return isSuccess;
    }

}
