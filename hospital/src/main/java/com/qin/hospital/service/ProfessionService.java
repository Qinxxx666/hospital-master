package com.qin.hospital.service;

import com.qin.hospital.entity.Profession;

import java.util.List;
import java.util.Map;

public interface ProfessionService {
    List<Profession> getProfessionList(Map<String, Object> map);

    Profession getProfessionById(Long id);
}
