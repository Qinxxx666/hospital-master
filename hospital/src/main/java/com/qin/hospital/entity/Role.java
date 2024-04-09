package com.qin.hospital.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (Role)实体类
 *
 * @author makejava
 * @since 2024-04-08 15:33:04
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "role")
public class Role implements Serializable {
    private static final long serialVersionUID = 848505931247001308L;
    /**
     * 角色id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色标签
     */
    private String label;
    /**
     * 角色描述
     */
    private String description;
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

