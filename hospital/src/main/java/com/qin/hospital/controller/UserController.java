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
    public RestResponse<String> addUser(@RequestHeader(value = "Authorization", required = false) String authorizationHeader, User user) {
        if (StringUtils.isEmpty(authorizationHeader))
        {
            return RestResponse.failure(10011, "用户未认证");
        }
        if (Boolean.TRUE.equals(redisTemplate.hasKey(authorizationHeader)))
        {
            return RestResponse.failure(10010, "登录时间过期");
        }
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
    public RestResponse<String> login(@RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                                      String userName, String password) {
        System.out.println("authorizationHeader:"+authorizationHeader);
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
            if(StringUtils.isEmpty(authorizationHeader)) {
                String token = JWTUtils.getToken(user.getId(), user.getUserName());
                user.setPassword("");
                redisTemplate.opsForValue().set(token, user);
                redisTemplate.expire(token, 1800, TimeUnit.SECONDS);
                return RestResponse.success(200, "登录成功", token);
            }
            else {
                redisTemplate.expire(authorizationHeader, 1800, TimeUnit.SECONDS);
                return RestResponse.success(200, "登录成功", authorizationHeader);
            }

        } else {
            return RestResponse.success(40002, "密码错误");
        }
    }

    @GetMapping("/verify")
    public RestResponse<String> verifyToken(@RequestHeader(value = "Authorization") String authorizationHeader) {
        log.info(authorizationHeader);
        if (StringUtils.isEmpty(authorizationHeader)) {
            return RestResponse.failure(403, "非法用户");
        }
        if (Boolean.FALSE.equals(redisTemplate.hasKey(authorizationHeader))) {

            return RestResponse.failure(401, "用户认证过期");
        }
        return RestResponse.success(200, "用户认证通过");
    }

    @GetMapping("/userInfo")
    public RestResponse<User> getUserInfo(@RequestHeader(value = "Authorization") String authorizationHeader) {
        if (StringUtils.isEmpty(authorizationHeader)) {
            return RestResponse.failure(403, "非法用户");
        }
        if (Boolean.FALSE.equals(redisTemplate.hasKey(authorizationHeader))) {

            return RestResponse.failure(401, "用户认证过期");
        }
        User user = (User) redisTemplate.opsForValue().get(authorizationHeader);
        return RestResponse.success(200, "用户认证通过", user);
    }
}
