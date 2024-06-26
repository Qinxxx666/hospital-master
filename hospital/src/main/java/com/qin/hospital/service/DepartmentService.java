package com.qin.hospital.service;

import com.qin.hospital.entity.Department;

import java.util.List;

/**
 * @author WanYue
 */
public interface DepartmentService {
    List<Department> getDepartmentListByParent(Long id);

    List<Department> getNoneParentDepartmentList();

    Department getDepartmentById(Long id);

    int addDepartment(Department department);

    int updateDepartment(Department department);

    int deleteDepartment(Long id);

    int deleteBatchIds(List<Long> ids);
}
