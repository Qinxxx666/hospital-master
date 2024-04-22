package com.qin.hospital.controller;

import com.qin.hospital.util.MinioUtils;
import com.qin.hospital.util.RestResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author WanYue
 * 文件上传
 */
@Log4j2
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    MinioUtils minioUtils;

    @PostMapping("/upload")
    public String uploadFile(MultipartFile file) {
        return minioUtils.uploadFile(file);
    }
}
