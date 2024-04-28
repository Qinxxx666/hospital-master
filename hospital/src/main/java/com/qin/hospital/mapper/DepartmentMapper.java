package com.qin.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qin.hospital.entity.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author WanYue
 */
public interface DepartmentMapper extends BaseMapper<Department> {
    List<Department>  getDepartmentListByParent(@Param(value = "id") Long id);

    List<Department> getNoneParentDepartmentList();
}
