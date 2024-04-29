package com.qin.hospital.controller;

import com.qin.hospital.VO.DepartmentInfoVO;
import com.qin.hospital.VO.DepartmentTreeVO;
import com.qin.hospital.VO.UserFormVO;
import com.qin.hospital.entity.Department;
import com.qin.hospital.entity.User;
import com.qin.hospital.service.DepartmentService;
import com.qin.hospital.service.DepartmentUserService;
import com.qin.hospital.service.UserService;
import com.qin.hospital.util.MinioUtils;
import com.qin.hospital.util.RestResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/info/{id}")
    public RestResponse<DepartmentInfoVO> getDepartmentInfoById(@PathVariable Long id) {
        DepartmentInfoVO vo = new DepartmentInfoVO();
        Department department = departmentService.getDepartmentById(id);
        vo.setCode(department.getCode());
        vo.setName(department.getName());
        vo.setDescription(department.getDescription());
        vo.setNumber(departmentUserService.getDepartmentUserCountByDepartmentId(id).toString() + "人");
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
