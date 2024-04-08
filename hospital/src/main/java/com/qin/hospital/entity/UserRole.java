package com.qin.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (UserRole)实体类
 *
 * @author makejava
 * @since 2024-04-08 15:35:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole implements Serializable {
    private static final long serialVersionUID = 257693924221477622L;
/**
     * 用户角色关联表id
     */
    private String id;
/**
     * 用户id
     */
    private String uId;
/**
     * 角色id
     */
    private String rId;
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

