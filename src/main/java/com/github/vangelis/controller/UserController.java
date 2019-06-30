package com.github.vangelis.controller;

import com.github.vangelis.model.UserDO;
import com.github.vangelis.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户请求控制器类
 *
 * @author Vangelis
 * @date 2019-06-30 10:40
 */
@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     *  查询所有的用户
     * @return 查询结果
     */
    public List<UserDO> listUsers() {
        return userService.listUsers();
    }


}
