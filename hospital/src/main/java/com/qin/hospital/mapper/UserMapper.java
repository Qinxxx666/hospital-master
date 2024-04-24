package com.qin.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qin.hospital.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author WanYue
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据查询条件返回数据
     *
     * @param user 用户信息
     * @return 用户列表
     */
    List<User> getUserList(User user);

    /**
     * 登录检验
     * @param map 用户名密码
     * @return 用户信息
     */
    User login(Map<String,String> map);

    Integer register(User user);
}
