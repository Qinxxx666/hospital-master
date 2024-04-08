package com.qin.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2024-04-08 15:29:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -22529841091740030L;
    /**
     * 用户Id
     */
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别(0为女，1为男)
     */
    private String sex;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 是否允许登录(0不允许，1允许)
     */
    private String enabledLogin;
    /**
     * 创建者
     */
    private String creator;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    /**
     * 修改者
     */
    private String modifier;
    /**
     * 修改时间
     */
    private LocalDateTime modifiedAt;
}

