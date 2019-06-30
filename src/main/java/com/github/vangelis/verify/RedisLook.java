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

    String lookName();
}
