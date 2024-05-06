package com.qin.hospital.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.qin.hospital.service.DepartmentService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

/**
 * (Department)实体类
 *
 * @author makejava
 * @since 2024-04-27 16:48:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@TableName(value = "department")
public class Department implements Serializable {
    private static final long serialVersionUID = -21528052322751781L;
    /**
     * 科室ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 科室代码
     */
    private String code;
    /**
     * 科室名称
     */
    private String name;
    /**
     * 父科室id
     */
    @TableField(value = "parent_id")
    private Department parentDepartment;
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

