package com.qin.hospital.service;

import com.qin.hospital.entity.Role;

import java.util.List;

/**
 * @Title: RoleService
 * @Author Qin
 * @Package com.qin.hospital.service
 * @Date 2024/4/9 16:48
 * @description: 角色管理功能的service
 */
public interface RoleService {
    /**
     * 功能点：
     * 1.角色信息列表展示
     * 2.新增角色，非空键：
     * 3.修改角色信息
     * 4.删除角色
     *
     */
    List<Role> getRoleList(Role role);
    int addRole(Role role);
    int updateRole(Role role);
    int deleteRole(Role role);
}
