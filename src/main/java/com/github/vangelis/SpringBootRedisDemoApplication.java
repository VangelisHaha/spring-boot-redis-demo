package com.github.vangelis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * spring boot 结合redis demo项目
 * @author   Vangelis
 * @date 2019/6/30 16:37
 */
@SpringBootApplication
@EnableCaching
public class SpringBootRedisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRedisDemoApplication.class, args);
    }

}
