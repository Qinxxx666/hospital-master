package com.qin.hospital.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

/**
 * (DepartmentUser)实体类
 *
 * @author makejava
 * @since 2024-04-28 09:39:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@TableName(value = "department_user")
public class DepartmentUser implements Serializable {
    private static final long serialVersionUID = -68281582601795746L;
    /**
     * 科室人员关联表Id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 人员ID
     */
    @TableField(value = "u_id")
    private User user;
    /**
     * 科室ID
     */
    @TableField(value = "d_id")
    private Department department;
    /**
     * 描述
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

