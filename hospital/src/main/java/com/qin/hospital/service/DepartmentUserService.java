package com.qin.hospital.service;

import com.qin.hospital.VO.UserFormVO;
import com.qin.hospital.entity.DepartmentUser;
import com.qin.hospital.entity.User;

import java.util.List;

/**
 * @author WanYue
 */
public interface DepartmentUserService {
    Long getDepartmentUserCountByDepartmentId(Long id);

    List<User> getDepartmentUserListByDepartmentId(Long id);

    Integer batchInsertDepartmentUser(List<DepartmentUser> departmentUserList);
}
