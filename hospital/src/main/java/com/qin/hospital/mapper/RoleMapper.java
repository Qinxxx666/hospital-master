package com.qin.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qin.hospital.entity.Role;
import com.qin.hospital.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Title: RoleMapper
 * @Author Qin
 * @Package com.qin.hospital.mapper
 * @Date 2024/4/9 16:42
 * @description: 角色管理的mapper
 */
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> getRoleList(Role role);
    int addRole(Role role);
    int updateRole(Role role);
    int deleteRole(Long id);

    List<Role> getRolesByUser(User user);
}
