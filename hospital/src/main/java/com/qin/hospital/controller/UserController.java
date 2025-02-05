package com.qin.hospital.controller;

import com.qin.hospital.vo.UserFormVO;
import com.qin.hospital.entity.File;
import com.qin.hospital.entity.User;
import com.qin.hospital.service.UserService;
import com.qin.hospital.util.JWTUtils;
import com.qin.hospital.util.MinioUtils;
import com.qin.hospital.util.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Log4j2
@RestController
@RequestMapping("/user")
@Tag(name = "user相关API", description = "全部user相关的API")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    MinioUtils minioUtils;

    @GetMapping("/getUserList")
    @Operation(summary = "用户查询GET请求", description = "列出所有用户列表")
    public List<UserFormVO> getUserInfo(User user) {
        List<UserFormVO> list = new ArrayList<>();
        for (User userVO : userService.getUserList(user)) {
            UserFormVO vo = new UserFormVO();
            vo.setId(userVO.getId().toString());
            vo.setUserName(userVO.getUserName());
            vo.setRealName(userVO.getRealName());
            vo.setAge(userVO.getAge());
            vo.setEmail(userVO.getEmail());
            vo.setPhoneNumber(userVO.getPhoneNumber());
            vo.setEnabledLogin(userVO.getEnabledLogin());
            vo.setRegisterTime(userVO.getRegisterTime());
            if (userVO.getAvatar() != null) {
                vo.setAvatar(minioUtils.getUrl(userVO.getAvatar().getPathName(), userVO.getAvatar().getName()));
            }
            list.add(vo);
        }
        return list;
    }

    @PostMapping("/add")
    public RestResponse<String> addUser(@RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                                        User user,
                                        @RequestParam(value = "avatarFile", required = false) MultipartFile multipartFile) {
        if (StringUtils.isEmpty(authorizationHeader)) {
            return RestResponse.failure(10011, "用户未认证");
        }
        if (Boolean.FALSE.equals(redisTemplate.hasKey(authorizationHeader))) {
            return RestResponse.failure(10010, "登录时间过期");
        }
        int rc;

        if (multipartFile != null) {
            File file = minioUtils.uploadFile("User", multipartFile);
            user.setAvatar(file);
        }
        try {
            user.setPassword("123456");
            user.setRegisterTime(LocalDateTime.now());
            rc = userService.register(user);
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
        System.out.println("authorizationHeader:" + authorizationHeader);
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
            if (StringUtils.isEmpty(authorizationHeader)) {
                String token = JWTUtils.getToken(user.getId(), user.getUserName());
                user.setPassword("");
                redisTemplate.opsForValue().set(token, user);
                redisTemplate.expire(token, 1800, TimeUnit.SECONDS);
                return RestResponse.success(200, "登录成功", token);
            } else {
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
