package com.qin.hospital.service;

import com.qin.hospital.VO.UserFormVO;
import com.qin.hospital.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author WanYue
 */
public interface UserService {
    List<User> getUserList(User user);

    int addUser(User user);

    User login(Map<String, String> map);

    Integer register(User user);

    List<User> getUserListByDepartmentId(Long id);

    User getUserById(Long id);
}
