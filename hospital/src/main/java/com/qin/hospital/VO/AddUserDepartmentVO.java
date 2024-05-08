package com.qin.hospital.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author WanYue
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserDepartmentVO implements Serializable {
    private List<Long> departmentListId;
    private List<Long> userIdList;
}
