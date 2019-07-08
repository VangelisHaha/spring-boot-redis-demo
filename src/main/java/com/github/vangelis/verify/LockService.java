package com.github.vangelis.verify;


import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.github.vangelis.config.RedisTemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 *  分布式锁Service
 * @author   Vangelis
 * @date 2019/6/30 21:07
 */
@Configuration
@ConditionalOnBean(RedisTemplateConfig.class)
public class LockService {
    @Autowired
   private   StringRedisTemplate stringRedisTemplate;
    /**
     *  锁前缀
     */
    private final String  REDIS_LOCK_PREFIX="lock:";

    /**
     *
     * @param lockKey 锁key
     * @param acquireTimeOut 重复获取锁有效时间
     * @param timeOut 锁的超时时间
     */
    String  getRedisLock(String lockKey, Long acquireTimeOut, Long timeOut){
        String lockId = UUID.randomUUID().toString();
        //获取过期的时间
        long endTime = System.currentTimeMillis() + acquireTimeOut;
        //循环获取锁
        while (System.currentTimeMillis()<endTime){
            if(stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK_PREFIX+lockKey,lockId,timeOut, TimeUnit.SECONDS)){
                // 缓存完成后返回缓存后的key
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
