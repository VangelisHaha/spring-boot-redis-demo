package com.github.vangelis.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户类
 *
 * @author Vangelis
 * @date 2019-06-30 10:16
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="user",indexes = {@Index(name = "idx_number",  columnList="number", unique = true)})
@org.hibernate.annotations.Table(appliesTo = "user",comment="用户信息表")
public class UserDO implements Serializable {
    public static final long serialVersionUID = 42L;

    /**
     *  主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     *  用户名
     */
    @Column(nullable = false,name="name",columnDefinition = "  varchar(11)  not null COMMENT '用户名'")
    private String name;

    /**
     *  性别
     */
    @Column(nullable = false,columnDefinition ="  varchar(4) not null  COMMENT '性别'" )
    private String sex;

    /**
     *  编号
     */
    @Column(nullable = false,columnDefinition ="  varchar(64) not null  COMMENT '编号'" )
    private String number;
    /**
     *  生日
     */
    @Column(nullable = false,columnDefinition ="  varchar(20)  not null  COMMENT '生日'" )
    private String birth;

    /**
     * 邮箱
     */
    @Column(nullable = false,columnDefinition ="  varchar(50) COMMENT '邮箱'" )
    private String email;

}
