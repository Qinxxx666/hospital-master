package com.qin.hospital.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * (File)实体类
 *
 * @author makejava
 * @since 2024-04-23 10:04:17
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class File implements Serializable {
    private static final long serialVersionUID = 281323650644588733L;

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 文件名。文件的名称，全名，包含“.”前的文件短名和后面的扩展名。
     */
    private String name;
    /**
     * 文件类型。文件类型。
     */
    private String fileType;
    /**
     * 大小(K)。文件的大小，以单位K计的字节数。
     */
    private Float size;
    /**
     * 扩展名。文件的扩展名
     */
    private String extensionName;
    /**
     * 文件路径
     */
    private String pathName;
    /**
     * 创建时间。
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    /**
     * 创建者
     */
    private String creator;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime modifiedAt;
    /**
     * 修改者
     */
    private String modifier;
    /**
     * 描述。文件的描述信息。
     */
    private String description;
}

