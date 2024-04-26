package com.qin.hospital.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (UserRole)实体类
 *
 * @author makejava
 * @since 2024-04-08 15:35:52
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user_role")
public class UserRole implements Serializable {
    private static final long serialVersionUID = 257693924221477622L;
    /**
     * 用户角色关联表id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 用户id
     */
    @TableField(value = "u_id")
    private String uId;
    /**
     * 角色id
     */
    @TableField(value = "r_id")
    private String rId;

    /**
     * 角色id
     */
    @TableField(value = "parent_id")
    private String parentId;

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

