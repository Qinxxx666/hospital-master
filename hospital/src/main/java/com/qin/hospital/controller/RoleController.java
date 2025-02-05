package com.qin.hospital.controller;

import com.qin.hospital.entity.Role;
import com.qin.hospital.service.RoleService;
import com.qin.hospital.util.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
 * @TODO /create
 * @TODO /delete
 * @TODO /listRoles
 * @TODO /listUsersByRole
 * @TODO /listPermissionsByRole
 * @TODO /update
 */
@Log4j2
@RestController
@RequestMapping("/role")
@Tag(name = "role相关API", description = "全部role相关的API")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/getRoleList")
    @Operation(summary = "角色查询的GET请求", description = "列出所有角色列表")
    public List<Role> getRoleList(Role role) {
        return roleService.getRoleList(role);
    }

    @PostMapping("/create")
    public RestResponse<String> create(Role role) {
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
