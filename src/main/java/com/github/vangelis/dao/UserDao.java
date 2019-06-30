package com.github.vangelis.dao;

import com.github.vangelis.model.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户Dao层
 *
 * @author Vangelis
 * @date 2019-06-30 10:36
 */
@Repository
public interface UserDao extends JpaRepository<UserDO,Long> {
}
