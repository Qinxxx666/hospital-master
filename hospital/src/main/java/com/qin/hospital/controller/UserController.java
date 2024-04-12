package com.qin.hospital.controller;

import com.qin.hospital.entity.User;
import com.qin.hospital.service.UserService;
import com.qin.hospital.util.JWTUtils;
import com.qin.hospital.util.RestResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
            return RestResponse.success(200, "添加成功");
        } else {
            return RestResponse.failure(501, "添加失败");
        }
    }
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @PostMapping("/login")
    public RestResponse<String> login(String userName, String password) {
        if (StringUtils.isEmpty(userName)) {
            return RestResponse.failure(40004, "用户名不能为空");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Map<String, String> map = new HashMap<>();
        map.put("userName", userName);
        User user = userService.login(map);
        if (user == null) {
            return RestResponse.success(40001, "用户未注册");
        }
        if (passwordEncoder.matches(password, user.getPassword())) {
            String token = JWTUtils.getToken(user.getId(), user.getUserName());
            user.setPassword("");
            redisTemplate.opsForValue().set(token, user);
            redisTemplate.expire(token, 1800, TimeUnit.SECONDS);
            return RestResponse.success(200, "登录成功", token);
        } else {
            return RestResponse.success(40002, "密码错误");
        }
    }
}
