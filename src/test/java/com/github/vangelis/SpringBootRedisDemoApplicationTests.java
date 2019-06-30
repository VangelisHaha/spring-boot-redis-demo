package com.github.vangelis;

import com.github.vangelis.dao.UserDao;
import com.github.vangelis.model.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRedisDemoApplicationTests {

    @Autowired
    private  UserDao userDao;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void contextLoads() {
    }


//    @Before
    public void insertUsers(){
        userDao.save(UserDO.builder()
                .name("Y")
                .birth("1995-01-01")
                .email("yayayayayy@vangelis.xyz")
                .number("adsfkaskdfjksadjf")
                .sex("女")
                .build());
        userDao.save(UserDO.builder()
                .name("W")
                .birth("1995-01-01")
                .email("waawawawa@vangelis.xyz")
                .number("123123123123")
                .sex("男")
                .build());
    }
    @Test
    public void selectUser(){
        System.out.println(userDao.findAll());
    }


    @Test
    public void redisTest(){
//        redisTemplate.opsForValue().set("key", "value");
////        System.out.println(redisTemplate.opsForValue().get("key"));

        stringRedisTemplate.opsForValue().set("haha", "asdf");
        System.out.println(stringRedisTemplate.opsForValue().get("haha"));
    }

}
