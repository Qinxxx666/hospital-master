package com.qin.hospital.controller;

import com.qin.hospital.VO.*;
import com.qin.hospital.entity.Department;
import com.qin.hospital.entity.DepartmentUser;
import com.qin.hospital.entity.User;
import com.qin.hospital.service.DepartmentService;
import com.qin.hospital.service.DepartmentUserService;
import com.qin.hospital.service.UserService;
import com.qin.hospital.util.MinioUtils;
import com.qin.hospital.util.RestResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author WanYue
 */

@RestController
@RequestMapping("/department")
@Log4j2
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/list")
    public RestResponse<List<DepartmentTreeVO>> getDepartmentList() {
        //顶级节点
        List<Department> departments = departmentService.getNoneParentDepartmentList();
        List<DepartmentTreeVO> departmentTreeVoList = new ArrayList<>();
        for (Department department : departments) {
            DepartmentTreeVO treeVo = new DepartmentTreeVO();
            treeVo.setLabel(department.getName());
            treeVo.setValue(department.getId().toString());
            getChildren(department, treeVo);
            departmentTreeVoList.add(treeVo);
        }

        return RestResponse.success(departmentTreeVoList);
    }

    @Autowired
    DepartmentUserService departmentUserService;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/info/{id}")
    public RestResponse<DepartmentInfoVO> getDepartmentInfoById(@PathVariable Long id) {
//        if (Boolean.TRUE.equals(redisTemplate.hasKey(id.toString()))) {
//            return RestResponse.success((DepartmentInfoVO) redisTemplate.opsForValue().get(id.toString()));
//        }
        DepartmentInfoVO vo = new DepartmentInfoVO();
        Department department = departmentService.getDepartmentById(id);
        vo.setCode(department.getCode());
        vo.setName(department.getName());
        vo.setDescription(department.getDescription());
        vo.setNumber(departmentUserService.getDepartmentUserCountByDepartmentId(id).toString() + "人");
//        redisTemplate.opsForValue().set(id.toString(), vo, 8, TimeUnit.HOURS);
        return RestResponse.success(vo);
    }

    @Autowired
    UserService userService;

    @Autowired
    MinioUtils minioUtils;

    @GetMapping("/user/list/{id}")
    public RestResponse<List<UserFormVO>> getDepartmentUserListByDepartmentId(@PathVariable Long id) {
        List<User> users = userService.getUserListByDepartmentId(id);
        if (users == null) {
            return RestResponse.success(new ArrayList<>());
        }
        List<UserFormVO> userFormVo = new ArrayList<>();
        for (User user : users) {
            UserFormVO vo = new UserFormVO();
            //long类型传到前端会丢失精度
            vo.setId(user.getId().toString());
            vo.setAge(user.getAge());
            vo.setSex(user.getSex());
            vo.setPhoneNumber(user.getPhoneNumber());
            vo.setDiploma(user.getDiploma());
            vo.setEmail(user.getEmail());
            vo.setRealName(user.getRealName());
            if (user.getAvatar() != null) {
                vo.setAvatar(minioUtils.getUrl(user.getAvatar().getPathName(), user.getAvatar().getName()));
            }
            vo.setEnabledLogin(user.getEnabledLogin());
            vo.setAddress(user.getAddress());
            if (user.getProfession() != null) {
                vo.setProfession(user.getProfession().getName());
            }
            userFormVo.add(vo);
        }
        return RestResponse.success(userFormVo);
    }

    @PostMapping("/add")
    public RestResponse<String> addDepartment(DepartmentInfoVO departmentInfo) {
        Department department = new Department();
        department.setParentDepartment(departmentService.getDepartmentById(departmentInfo.getParentId()));
        department.setName(departmentInfo.getName());
        department.setCode(departmentInfo.getCode());
        department.setIsReal(departmentInfo.getIsReal());
        department.setDescription(departmentInfo.getDescription());
        int rc = departmentService.addDepartment(department);
        if (rc < 0) {
            return RestResponse.failure(201, "添加失败");
        }
        return RestResponse.success(200);
    }

    @PostMapping("/delete")
    public RestResponse<String> deleteDepartment(@RequestBody List<String> ids) {
        List<Long> idList = ids.stream().map(Long::valueOf).collect(Collectors.toList());
        int rc = departmentService.deleteBatchIds(idList);
        if (rc < 0) {
            return RestResponse.failure(201, "删除失败");
        }
        return RestResponse.success(200, "删除成功");
    }


    @GetMapping("/getUserList/{id}")
    public RestResponse<List<LabelValueVO>> getUserList(@PathVariable Long id) {
        List<User> departmentUserList = departmentUserService.getDepartmentUserListByDepartmentId(id);
        List<User> userList = userService.getUserList(new User());
        List<LabelValueVO> userFormVo = new ArrayList<>();
        for (User user : userList) {
            LabelValueVO vo = new LabelValueVO();
            if (departmentUserList.contains(user)) {
                vo.setDisabled(true);
            }
            vo.setLabel(user.getRealName());
            vo.setValue(user.getId().toString());
            userFormVo.add(vo);
        }
        return RestResponse.success(userFormVo);
    }

    @PostMapping("/addUser")
    public RestResponse<String> addUserToDepartment(@RequestBody AddUserDepartmentVO addUserDepartmentVO) {
        List<DepartmentUser> list = new ArrayList<>();
        for (Long dId : addUserDepartmentVO.getDepartmentListId()) {
            for (Long id : addUserDepartmentVO.getUserIdList()) {
                DepartmentUser departmentUser = new DepartmentUser();
                departmentUser.setDepartment(departmentService.getDepartmentById(dId));
                departmentUser.setUser(userService.getUserById(id));
                list.add(departmentUser);
            }
        }
        int rc = departmentUserService.batchInsertDepartmentUser(list);
        if (rc < 0) {
            return RestResponse.failure(201, "failure");
        }
        return RestResponse.success(200, "success");
    }

    @PostMapping("/remove")
    public RestResponse<String> removeUserFromDepartment(@RequestBody List<String> departmentId, String userId) {
        log.info("departmentId:{} userId:{}", departmentId, userId);
        if (departmentId.isEmpty() && StringUtils.isBlank(userId)) {
            return RestResponse.failure(201, "id不能为空");
        }
        List<Long> collect = departmentId.stream().map(Long::valueOf).collect(Collectors.toList());
        int rc = departmentUserService.deleteDepartmentUser(collect, Long.valueOf(userId));
        if (rc < 0) {
            return RestResponse.failure(201, "failure");
        }
        return RestResponse.success(200, "success");
    }

    private void getChildren(Department department, DepartmentTreeVO treeVo) {
        List<Department> departmentList = departmentService.getDepartmentListByParent(department.getId());
        if (departmentList.isEmpty()) {
            return;
        }
        for (Department child : departmentList) {
            DepartmentTreeVO childTreeVo = new DepartmentTreeVO();
            childTreeVo.setLabel(child.getName());
            childTreeVo.setValue(child.getId().toString());
            if (treeVo.getChildren() == null) {
                treeVo.setChildren(new ArrayList<>());
            }
            treeVo.getChildren().add(childTreeVo);
            getChildren(child, childTreeVo);
        }
    }
}
