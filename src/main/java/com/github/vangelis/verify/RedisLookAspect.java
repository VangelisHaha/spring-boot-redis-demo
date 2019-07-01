package com.github.vangelis.verify;


import cn.hutool.core.util.StrUtil;
import com.github.vangelis.util.RedisLookException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletResponse;

/**
 *  分布式锁的AOP拦截
 * @author   Vangelis
 * @date 2019/6/30 21:27
 */
@Aspect
@Configuration
@ConditionalOnBean(LockService.class)
public class RedisLookAspect {
    @Autowired
    private  LockService lockService;

    /**
     * 自定义切点为redis注解
     */
    @Pointcut("@annotation(RedisLook)")
    public void redisLock(){

    }
    /**
     * 使用切点，封装方法
     */
    @Around("redisLock()")
    public Object lockRedis(ProceedingJoinPoint joinPoint) throws Throwable {
        String lockKey = getRedisLook(joinPoint).lookName();
        //调用redis，传入超时时间和等待时间
        String redisLock = lockService.getRedisLock(lockKey,getRedisLook(joinPoint).acquireTimeOut(),getRedisLook(joinPoint).timeOut());
        System.out.println("redis锁开启");
        //如果获取到的锁key不是空
        if(StrUtil.isNotBlank(redisLock)){
            Object object = joinPoint.proceed();
            //代码走完之后释放锁
            lockService.releaseLock(lockKey,redisLock);
            return object;
        }else{
            // 抛出异常
            throw new RedisLookException("系统繁忙，请稍后再试", HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }

    /**
     * 获取注解信息
     */
    private RedisLook getRedisLook(ProceedingJoinPoint joinPoint){
        return ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(RedisLook.class);
    }
}
