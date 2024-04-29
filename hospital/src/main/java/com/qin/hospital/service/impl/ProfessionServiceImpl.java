package com.qin.hospital.service.impl;

import com.qin.hospital.entity.Profession;
import com.qin.hospital.mapper.ProfessionMapper;
import com.qin.hospital.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author WanYue
 */
@Service
public class ProfessionServiceImpl implements ProfessionService {

    @Autowired
    ProfessionMapper professionMapper;
    @Override
    public List<Profession> getProfessionList(Map<String, Object> map) {
        return professionMapper.selectByMap(map);
    }

    @Override
    public Profession getProfessionById(Long id) {
        return professionMapper.selectById(id);
    }
}
