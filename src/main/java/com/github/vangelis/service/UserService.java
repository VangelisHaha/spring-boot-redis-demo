package com.github.vangelis.service;

import com.github.vangelis.model.UserDO;

import java.util.List;

/**
 * User Service层
 *
 * @author Vangelis
 * @date 2019-06-30 10:39
 */

public interface UserService {
    /**
     *  查询所有的用户
     * @return 查询结果
     */
    List<UserDO> listUsers();
}
