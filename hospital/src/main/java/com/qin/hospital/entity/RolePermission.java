package com.qin.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (RolePermission)实体类
 *
 * @author makejava
 * @since 2024-04-08 15:35:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermission implements Serializable {
    private static final long serialVersionUID = 709424165099131693L;
/**
     * 角色权限关联表id
     */
    private String id;
/**
     * 角色id
     */
    private String rId;
/**
     * 权限id
     */
    private String pId;
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

