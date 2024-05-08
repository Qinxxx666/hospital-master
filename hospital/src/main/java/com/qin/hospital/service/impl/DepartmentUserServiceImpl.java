package com.qin.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qin.hospital.entity.DepartmentUser;
import com.qin.hospital.entity.User;
import com.qin.hospital.mapper.DepartmentUserMapper;
import com.qin.hospital.service.DepartmentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<User> getDepartmentUserListByDepartmentId(Long id) {
        return departmentUserMapper.getDepartmentUserListByDepartmentId(id);
    }

    @Override
    public Integer batchInsertDepartmentUser(List<DepartmentUser> departmentUserList) {
        return departmentUserMapper.batchInsertDepartmentUser(departmentUserList);
    }

    @Override
    public Integer deleteDepartmentUser(List<Long> departmentId, Long userId) {
        return departmentUserMapper.deleteDepartmentUser(departmentId, userId);
    }
}
