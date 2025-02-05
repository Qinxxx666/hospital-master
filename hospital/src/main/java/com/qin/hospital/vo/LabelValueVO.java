package com.qin.hospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author WanYue
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabelValueVO implements Serializable {
    private String label;
    private Object value;
    private Boolean disabled;
}
