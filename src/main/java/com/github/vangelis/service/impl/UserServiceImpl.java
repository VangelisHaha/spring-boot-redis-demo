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

    /**
     * 添加一个用户
     *
     * @param userDO 添加对象
     * @return 添加结果
     */
    @Override
    public String addUser(UserDO userDO) {
        userDao.saveAndFlush(userDO);
        return "新增成功";
    }

    /**
     * 根据id更新一个用户
     *
     * @param userDO 需要更新的对象
     * @return 添加结果
     */
    @Override
    public String updateUser(UserDO userDO) {
        userDao.saveAndFlush(userDO);
        return "更新成功";
    }

    /**
     * 根据id删除一个对象
     *
     * @param userId 用户id
     * @return 删除结果
     */
    @Override
    public String deleteUser(Long userId) {
        if (userDao.existsById(userId)) {
            userDao.deleteById(userId);
        }
        return "删除成功";
    }

    /**
     * 根据用户id查询
     *
     * @param userId 用户id
     * @return 查询结果
     */
    @Override
    public UserDO getUserById(Long userId) {
        return userDao.findById(userId)
                .orElse(null);
    }
}
