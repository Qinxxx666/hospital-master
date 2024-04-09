package com.qin.hospital.mapper;

import com.qin.hospital.entity.User;

import java.util.List;

/**
 * @author WanYue
 */
public interface UserMapper {
    /**
     * 根据查询条件返回数据
     *
     * @param user 用户信息
     * @return 用户列表
     */
    List<User> getUserList(User user);
}
