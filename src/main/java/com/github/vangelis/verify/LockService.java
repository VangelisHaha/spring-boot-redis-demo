package com.github.vangelis.verify;


import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 *  分布式锁Service
 * @author   Vangelis
 * @date 2019/6/30 21:07
 */
@Component
public class LockService {
    @Autowired
   private   StringRedisTemplate stringRedisTemplate;
    private final String  REDIS_LOCK_PREFIX="lock:";

    /**
     *
     * @param lockKey 锁key
     * @param acquireTimeOut 重复获取锁有效时间
     * @param timeOut 锁的超时时间
     */
    String  getRedisLock(String lockKey, Long acquireTimeOut, Long timeOut){
        String lockId = UUID.randomUUID().toString();
        int lockTime=(int)(timeOut/acquireTimeOut);
        //获取过期的时间
        long endTime = System.currentTimeMillis() + acquireTimeOut;
        //循环获取锁
        while (System.currentTimeMillis()<endTime){
            if(stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK_PREFIX+lockKey,lockId)){
                //可以成功设置,也就是key不存在
                stringRedisTemplate.expire(REDIS_LOCK_PREFIX+lockKey, lockTime, TimeUnit.SECONDS);
                return lockId;
            }
        }
        return null;
    }

    /**
     * 释放锁
     * @param lockKey 锁key
     * @param lockId  锁id
     */
    void releaseLock(String lockKey, String lockId) {
        if (StrUtil.isNotEmpty(lockId)&&StrUtil.isNotEmpty(lockKey)) {
            String currentValue = stringRedisTemplate.opsForValue().get(REDIS_LOCK_PREFIX+lockKey);
            //判断锁id和key都符合才允许解锁
            if (StrUtil.isNotEmpty(currentValue) && lockId.equals(currentValue)) {
                //删除key
                stringRedisTemplate.opsForValue().getOperations().delete(REDIS_LOCK_PREFIX+lockKey);
            }
        }
    }
}
