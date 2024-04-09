package com.qin.hospital.controller;

import com.qin.hospital.entity.User;
import com.qin.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ResponseBody
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("getUserList")
    public List<User> getUserInfo(User user)
    {
        return userService.getUserList(user);
    }
}
