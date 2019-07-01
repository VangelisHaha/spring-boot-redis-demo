package com.github.vangelis.verify;


import cn.hutool.core.util.StrUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *  分布式锁的AOP拦截
 * @author   Vangelis
 * @date 2019/6/30 21:27
 */
@Aspect
@Component
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
     * 锁的有效时间10s
     */
    private final Long timeOut = 10000L;

    /**
     * 循环获取锁1s
     */
    private final Long acquireTimeOut = 3000L;

    /**
     * 使用切点，封装方法
     */
    @Around("redisLock()")
    public Object lockRedis(ProceedingJoinPoint joinPoint) throws Throwable {
        String redisName = getRedisName(joinPoint);
        String redisLock = lockService.getRedisLock(redisName,acquireTimeOut,timeOut);
        System.out.println("redis锁开启");
        //如果获取到的锁key不是空
        if(StrUtil.isNotBlank(redisLock)){
            Object object = joinPoint.proceed();
            //代码走完之后释放锁
            lockService.releaseLock(redisName,redisLock);
            return object;
        }else{
            return "当前系统繁忙，请重试";
        }
    }

    private String getRedisName(ProceedingJoinPoint joinPoint){
        RedisLook look = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(RedisLook.class);
        return look.lookName();
    }
}
