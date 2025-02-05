package com.qin.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qin.hospital.entity.Department;
import com.qin.hospital.mapper.DepartmentMapper;
import com.qin.hospital.service.DepartmentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WanYue
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public List<Department> getDepartmentListByParent(Long id) {
        return departmentMapper.getDepartmentListByParent(id);
    }

    @Override
    public List<Department> getNoneParentDepartmentList() {
        return departmentMapper.getNoneParentDepartmentList();
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentMapper.selectById(id);
    }

    @Override
    public int addDepartment(Department department) {
        return departmentMapper.insert(department);
    }

    @Override
    public int updateDepartment(Department department) {
        return departmentMapper.updateById(department);
    }

    @Override
    public int deleteDepartment(Long id) {
        return departmentMapper.deleteById(id);
    }

    @Override
    public int deleteBatchIds(List<Long> ids) {
        return departmentMapper.deleteBatchIds(ids);
    }
}
