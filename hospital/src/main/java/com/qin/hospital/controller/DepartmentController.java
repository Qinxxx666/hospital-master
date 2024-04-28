package com.qin.hospital.controller;

import com.qin.hospital.VO.DepartmentTreeVo;
import com.qin.hospital.entity.Department;
import com.qin.hospital.service.DepartmentService;
import com.qin.hospital.util.RestResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    public RestResponse<List<DepartmentTreeVo>> getDepartmentList() {
        //顶级节点
        List<Department> departments = departmentService.getNoneParentDepartmentList();
        List<DepartmentTreeVo> departmentTreeVoList = new ArrayList<>();
        for (Department department : departments) {
            DepartmentTreeVo treeVo = new DepartmentTreeVo();
            treeVo.setLabel(department.getName());
            treeVo.setValue(department.getId());
            getChildren(department, treeVo);
            departmentTreeVoList.add(treeVo);
        }

        return RestResponse.success(departmentTreeVoList);
    }

    private void getChildren(Department department, DepartmentTreeVo treeVo) {

        List<Department> departmentList = departmentService.getDepartmentListByParent(department.getId());
        if (departmentList.isEmpty()) {
            return;
        }

        for (Department child : departmentList) {
            DepartmentTreeVo childTreeVo = new DepartmentTreeVo();
            childTreeVo.setLabel(child.getName());
            childTreeVo.setValue(child.getId());
            if (treeVo.getChildren() == null) {
                treeVo.setChildren(new ArrayList<>());
            }
            treeVo.getChildren().add(childTreeVo);
            getChildren(child, childTreeVo);
        }
    }
}
