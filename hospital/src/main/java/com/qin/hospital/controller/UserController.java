package com.qin.hospital.controller;

import com.qin.hospital.entity.User;
import com.qin.hospital.service.UserService;
import com.qin.hospital.util.RestResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/login")
    public RestResponse<User> login(@RequestParam("userName") String userName, @RequestParam("password") String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Map<String, String> map = new HashMap<>();
        map.put("userName", userName);
        User user = userService.login(map);
        if (user == null)
        {
            return RestResponse.success(40001, "用户未注册");
        }
        if (passwordEncoder.matches(password, user.getPassword()))
        {
            return RestResponse.success(20000, "登录成功", user);
        }
        else
        {
            return RestResponse.success(40002, "密码错误");
        }
    }
}
