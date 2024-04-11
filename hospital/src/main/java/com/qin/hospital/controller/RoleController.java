package com.qin.hospital.controller;

import com.qin.hospital.entity.Role;
import com.qin.hospital.util.RestResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Title: RoleController
 * @Author Qin
 * @Package com.qin.hospital.controller
 * @Date 2024/4/9 21:32
 * @description: 角色的controller层
 */
@Log4j2
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    com.qin.hospital.service.impl.RoleServiceImpl roleService;

    @GetMapping("/getRoleList")
    public List<Role> getRoleList(Role role) {
        return roleService.getRoleList(role);
    }

    @PostMapping("/addRole")
    public RestResponse<String> addRole(Role role) {
        int rc;
        try {
            rc = roleService.addRole(role);
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
