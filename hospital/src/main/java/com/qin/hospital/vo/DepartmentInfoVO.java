package com.qin.hospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author WanYue
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentInfoVO implements Serializable {
    private Long id;
    private String code;
    private String name;
    private String description;
    private String number;
    private String isReal;
    private Long parentId;
}
