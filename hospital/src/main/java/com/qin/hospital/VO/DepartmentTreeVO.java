package com.qin.hospital.VO;

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
