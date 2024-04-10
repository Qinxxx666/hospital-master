package com.qin.hospital.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2024-04-08 15:29:13
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user")
public class User implements Serializable {
    private static final long serialVersionUID = -22529841091740030L;
    /**
     * 用户Id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 用户名
     */
    @TableField(value = "username")
    private String userName;
    /**
     * 真实姓名
     */
    @TableField(value = "realname")
    private String realName;
    /**
     * 密码
     */
    private String password;
    /**
     * 密码加盐
     */
    private String salt;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别(0为女，1为男)
     */
    private String sex;
    /**
     * 手机号码
     */
    @TableField(value = "phonenumber")
    private String phoneNumber;
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
     * 注册时间
     */
    @TableField(value = "registertime")
    private LocalDateTime registerTime;
    /**
     * 创建者
     */
    private String creator;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    /**
     * 修改者
     */
    private String modifier;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime modifiedAt;
}

