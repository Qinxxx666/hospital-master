package com.qin.hospital.VO;

import lombok.Data;

import java.util.List;

/**
 * @author WanYue
 */
@Data
public class DepartmentTreeVo {

    private String label;
    private Long value;
    private List<DepartmentTreeVo> children;
}
