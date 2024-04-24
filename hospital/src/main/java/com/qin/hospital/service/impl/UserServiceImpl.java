package com.qin.hospital.service.impl;

import com.qin.hospital.entity.User;
import com.qin.hospital.mapper.UserMapper;
import com.qin.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author WanYue
 */
@Service
public class UserServiceImpl implements UserService {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> getUserList(User user) {
        return userMapper.getUserList(user);
    }

    @Override
    public int addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.insert(user);
    }

    @Override
    public User login(Map<String, String> map) {
        return userMapper.login(map);
    }

    @Override
    public Integer register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.register(user);
    }
}
