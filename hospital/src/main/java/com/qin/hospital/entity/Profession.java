package com.qin.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.io.Serializable;

/**
 * (Profession)实体类
 *
 * @author makejava
 * @since 2024-04-26 17:20:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Profession implements Serializable {
    private static final long serialVersionUID = -39456538978152143L;
/**
     * 专业ID
     */
    private Long id;
/**
     * 专业编码
     */
    private String code;
/**
     * 专业名称
     */
    private String name;
/**
     * 专业描述
     */
    private String description;
/**
     * 创建者
     */
    private String creator;
/**
     * 创建时间
     */
    private Date createdAt;
/**
     * 修改者
     */
    private String modifier;
/**
     * 修改时间
     */
    private Date modifiedAt;

}

