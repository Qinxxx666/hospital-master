package com.qin.hospital.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (RolePermission)实体类
 *
 * @author makejava
 * @since 2024-04-08 15:35:07
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "role_permission")
public class RolePermission implements Serializable {
    private static final long serialVersionUID = 709424165099131693L;
    /**
     * 角色权限关联表id
     */
    private String id;
    /**
     * 角色id
     */
    @TableField(value = "r_id")
    private String rId;
    /**
     * 权限id
     */
    @TableField(value = "p_id")
    private String pId;
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

