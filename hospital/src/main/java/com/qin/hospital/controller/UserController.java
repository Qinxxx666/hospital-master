package com.qin.hospital.controller;

import com.qin.hospital.entity.User;
import com.qin.hospital.service.UserService;
import com.qin.hospital.util.RestResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getUserList")
    public List<User> getUserInfo(User user) {
        return userService.getUserList(user);
    }

    @PostMapping("/add")
    public RestResponse<String> addUser(User user) {
        int rc;
        try {
            rc = userService.addUser(user);
        } catch (Exception e) {
            log.error(e);
            rc = -1;
        }
        if (rc == 1) {
            return RestResponse.success(200,"添加成功");
        } else {
            return RestResponse.failure(501,"添加失败");
        }
    }
}
