package com.qin.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qin.hospital.entity.DepartmentUser;
import com.qin.hospital.mapper.DepartmentUserMapper;
import com.qin.hospital.service.DepartmentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WanYue
 */
@Service
public class DepartmentUserServiceImpl implements DepartmentUserService {

    @Autowired
    DepartmentUserMapper departmentUserMapper;
    @Override
    public Long getDepartmentUserCountByDepartmentId(Long id) {
        QueryWrapper<DepartmentUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("d_id", id);
        return departmentUserMapper.selectCount(queryWrapper);
    }
}
