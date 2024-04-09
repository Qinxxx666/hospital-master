package com.qin.hospital.service.Impl;

import com.qin.hospital.entity.Role;
import com.qin.hospital.mapper.RoleMapper;
import com.qin.hospital.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: RoleService
 * @Author Qin
 * @Package com.qin.hospital.service.Impl
 * @Date 2024/4/9 16:49
 * @description: 角色管理接口的实现类
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> getRoleList(Role role) {
        return roleMapper.getRoleList(role);
    }

    @Override
    public int addRole(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.updateById(role);
    }

    @Override
    public int deleteRole(Role role) {
        return roleMapper.deleteById(role.getId());
    }
}
