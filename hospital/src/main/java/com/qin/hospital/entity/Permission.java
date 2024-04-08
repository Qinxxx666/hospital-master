package com.qin.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (Permission)实体类
 *
 * @author makejava
 * @since 2024-04-08 15:34:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission implements Serializable {
    private static final long serialVersionUID = 169693021157202336L;
/**
     * 权限id
     */
    private String id;
/**
     * 权限名称
     */
    private String name;
/**
     * 权限描述
     */
    private String description;
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

