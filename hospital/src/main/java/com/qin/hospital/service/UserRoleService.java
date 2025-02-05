package com.qin.hospital.service;

import com.qin.hospital.entity.Role;
import com.qin.hospital.entity.User;

/**
 * @Title: UserRoleService
 * @Author Qin
 * @Package com.qin.hospital.service
 * @Version 1.0
 * @Date 2024/4/23 17:06
 * @description:
 */
public interface UserRoleService {
    int addRelation(Long uid, Long rid);
}
