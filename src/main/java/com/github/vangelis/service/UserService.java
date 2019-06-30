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

    /**
     *  添加一个用户
     * @param userDO 添加对象
     * @return 添加结果
     */
    String addUser(UserDO userDO);

    /**
     *  根据id更新一个用户
     * @param userDO 需要更新的对象
     * @return 添加结果
     */
    String updateUser(UserDO userDO);

    /**
     *  根据id删除一个对象
     * @param userId 用户id
     * @return 删除结果
     */
    String deleteUser(Long userId);
    /**
     *  根据用户id查询
     * @return 查询结果
     */
    UserDO getUserById(Long userId);
}
