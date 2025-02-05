package com.qin.hospital.util;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.qin.hospital.config.MinioConfig;
import com.qin.hospital.entity.File;
import com.qin.hospital.service.FileService;
import io.minio.BucketExistsArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Log4j2
public class MinioUtils {

    @Autowired
    MinioConfig prop;

    @Autowired
    FileService fileService;

    @Resource
    private MinioClient minioClient;

    @SneakyThrows(Exception.class)
    private void createBucket(String bucketName) {

    }

    private static final String ROOTPATH = "hospital";

    /**
     * 判断Bucket是否存在，true：存在，false：不存在
     *
     * @param bucketName 桶名
     * @return true or false
     */
    @SneakyThrows(Exception.class)
    public boolean bucketExists(String bucketName) {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    /**
     * 文件上传
     *
     * @param pathName 文件路径（只有一个路径时不用带 '/', 若需要组合多个路径请使用‘/’）
     * @param file     文件
     * @return file 类
     */
    @Transactional
    public File uploadFile(String pathName, MultipartFile file) {
        //判断文件路径是否有文件存在
        if (StringUtils.isBlank(pathName)) {
            throw new IllegalArgumentException("文件路径不能为空");
        }
        String originalFilename = file.getOriginalFilename();
        if (StringUtils.isBlank(originalFilename)) {
            throw new IllegalArgumentException("文件名不能为空");
        }
        //java读取文件
        File file1 = new File();
        IdentifierGenerator identifierGenerator = new DefaultIdentifierGenerator();
        Long id = (Long) identifierGenerator.nextId(new File());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        //定义文件路径：根路径（hospital）/ 用户定义路径 / 当前日期 / 数据库对应ID / 文件名
        String filePath = ROOTPATH + "/" + pathName + "/" + formatter.format(LocalDateTime.now());
        filePath += "/" + id;

        try {
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(prop.getBucketName())
                    .object(filePath + "/" + originalFilename)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build();
            minioClient.putObject(putObjectArgs);
            File f = new File();

            f.setId(id);
            f.setName(originalFilename);
            f.setPathName(filePath);
            f.setSize((float) file.getSize());
            f.setFileType(file.getContentType());
            f.setExtensionName(StringUtils.substringAfterLast(originalFilename, "."));

            if (fileService.insert(f) == 1) {
                file1 = f;
            } else {
                throw new RuntimeException("文件信息保存失败");
            }

        } catch (Exception e) {
            log.error("文件上传失败: {}", e.getMessage(), e);
        }
        return file1;
    }

    public String getUrl(String pathName, String fileName) {
        String url;
        try {
            url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(prop.getBucketName())
                    .object(pathName + "/" + fileName)
                    .expiry(60 * 60)
                    .method(io.minio.http.Method.GET)
                    .build()
            );
        } catch (Exception e) {
            url = "";
            log.error("获取文件URL失败: {}", e.getMessage(), e);
        }
        return url;
    }
}

