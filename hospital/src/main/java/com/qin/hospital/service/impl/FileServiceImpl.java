package com.qin.hospital.service.impl;

import com.qin.hospital.entity.File;
import com.qin.hospital.mapper.FileMapper;
import com.qin.hospital.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author WanYue
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileMapper fileMapper;
    @Override
    public File selectById(Long id) {
        return fileMapper.selectById(id);
    }
    @Override
    public Integer insert(File file) {
        return fileMapper.insert(file);
    }

    @Override
    public Integer updateById(File file) {
        return fileMapper.updateById(file);
    }

    @Override
    public Integer deleteById(Long id) {
        return fileMapper.deleteById(id);
    }
}
