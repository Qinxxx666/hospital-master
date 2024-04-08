package com.qin.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (Role)实体类
 *
 * @author makejava
 * @since 2024-04-08 15:33:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    private static final long serialVersionUID = 848505931247001308L;
/**
     * 角色id
     */
    private String id;
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

