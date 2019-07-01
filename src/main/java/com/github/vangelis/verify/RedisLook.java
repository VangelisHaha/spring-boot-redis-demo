package com.github.vangelis.verify;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Redis实现的分布式锁 注解
 *
 * @author Vangelis
 * @date 2019-06-30 20:54
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisLook {

    /**
     *  锁名字(key)
     */
    String lookName();

    /**
     * 锁的超时时间(毫秒)  锁的有效期。即使没获得锁，自动过期
     */
    long  timeOut() default 10000L;

    /**
     *  循环获取锁的等待时间(毫秒)  线程没有获取锁会在这个时间段重复去获取锁
     */
    long acquireTimeOut() default 3000L;
}
