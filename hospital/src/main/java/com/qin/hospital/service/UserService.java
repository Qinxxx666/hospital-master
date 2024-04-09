package com.qin.hospital.service;

import com.qin.hospital.entity.User;

import java.util.List;

/**
 * @author WanYue
 */
public interface UserService {
    List<User> getUserList(User user);

    int addUser(User user);
}
