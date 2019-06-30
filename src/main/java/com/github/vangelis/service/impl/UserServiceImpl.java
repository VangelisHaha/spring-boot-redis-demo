package com.github.vangelis.service.impl;

import com.github.vangelis.dao.UserDao;
import com.github.vangelis.model.UserDO;
import com.github.vangelis.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User Service层实现
 *
 * @author Vangelis
 * @date 2019-06-30 10:39
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    /**
     * 查询所有的用户
     *
     * @return 查询结果
     */
    @Override
    public List<UserDO> listUsers() {
        return userDao.findAll();
    }
}
