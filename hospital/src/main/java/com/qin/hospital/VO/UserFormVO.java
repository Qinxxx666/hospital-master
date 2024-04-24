package com.qin.hospital.VO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.qin.hospital.entity.File;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author WanYue
 */
@Data
public class UserFormVO {

    private Long id;

    @TableField(value = "username")
    private String userName;
    /**
     * 真实姓名
     */
    @TableField(value = "realname")
    private String realName;
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
     * 是否允许登录(0不允许，1允许)
     */
    @TableField(value = "registertime")
    private LocalDateTime registerTime;
}
