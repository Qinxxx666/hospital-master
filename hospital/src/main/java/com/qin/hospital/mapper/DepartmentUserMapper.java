package com.qin.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qin.hospital.entity.DepartmentUser;
import com.qin.hospital.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author WanYue
 */
public interface DepartmentUserMapper extends BaseMapper<DepartmentUser> {
    List<User> getDepartmentUserListByDepartmentId(@Param(value = "departmentId") Long departmentId);

    Integer batchInsertDepartmentUser(List<DepartmentUser> departmentUserList);

    Integer deleteDepartmentUser(@Param(value = "departmentId") List<Long> departmentId, @Param(value = "userId") Long userId);
}
