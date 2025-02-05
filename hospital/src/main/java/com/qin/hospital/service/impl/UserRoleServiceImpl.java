package com.qin.hospital.service.impl;

import com.qin.hospital.entity.Role;
import com.qin.hospital.entity.User;
import com.qin.hospital.entity.UserRole;
import com.qin.hospital.mapper.UserRoleMapper;
import com.qin.hospital.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title: UserRoleServiceImpl
 * @Author Qin
 * @Package com.qin.hospital.service.impl
 * @Version 1.0
 * @Date 2024/4/23 17:08
 * @description:
 */
@Service
public class UserRoleServiceImpl implements UserRoleService{
    @Autowired
    UserRoleMapper userRoleMapper;
    @Override
    public int addRelation(Long uid, Long rid) {
        UserRole userRole = new UserRole();
        userRole.setUId(uid);
        userRole.setRId(rid);
        userRoleMapper.insert(userRole);
        return 0;
    }
}
