package com.qin.hospital.service;

import com.qin.hospital.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author WanYue
 */
public interface UserService {
    List<User> getUserList(User user);

    int addUser(User user);

    User login(Map<String,String> map);
}
