package com.github.vangelis.controller;

import com.github.vangelis.model.UserDO;
import com.github.vangelis.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("users")
    public List<UserDO> listUsers() {
        return userService.listUsers();
    }
    /**
     *  根据用户id查询
     * @return 查询结果
     */
    @GetMapping("user/{userId}")
    public UserDO getUserById(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
    }


    /**
     *  添加一个用户
     * @param userDO 添加对象
     * @return 添加结果
     */
    @PostMapping("user")
    public String addUser(@RequestBody UserDO userDO) {
        return userService.addUser(userDO);
    }

    /**
     *  根据id更新一个用户
     * @param userDO 需要更新的对象
     * @return 添加结果
     */
    @PutMapping("user")
    public String updateUser(@RequestBody UserDO userDO) {
        if (userDO.getId()==null) {
            return "添加失败，请传入id";
        }
        return userService.updateUser(userDO);
    }

    /**
     *  根据id删除一个对象
     * @param userId 用户id
     * @return 删除结果
     */
    @DeleteMapping("user/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId) {
        return userService.deleteUser(userId);
    }


}
