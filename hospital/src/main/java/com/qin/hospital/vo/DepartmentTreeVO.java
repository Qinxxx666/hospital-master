package com.qin.hospital.vo;

import lombok.Data;

import java.util.List;

/**
 * @author WanYue
 */
@Data
public class DepartmentTreeVO {

    private String label;
    private String value;
    private List<DepartmentTreeVO> children;
}
