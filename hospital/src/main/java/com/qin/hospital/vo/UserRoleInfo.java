package com.qin.hospital.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: UserRoleInfo
 * @Author Qin
 * @Package com.qin.hospital.vo
 * @Version 1.0
 * @Date 2024/4/22 11:30
 * @description:用户信息的实体类
 */
@Data
public class UserRoleInfo implements Serializable {
    private static final long serialVersionUID = -22529841091740030L;
    /**
     * Id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 用户Id
     */
    private Long u_id;
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
     * 角色id
     */
    private Long r_id;
    /**
     * 角色名称
     */
    private String name;

}
