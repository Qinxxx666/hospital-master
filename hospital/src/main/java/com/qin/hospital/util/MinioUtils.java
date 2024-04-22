package com.qin.hospital.util;

import com.qin.hospital.config.MinioConfig;
import io.minio.BucketExistsArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Log4j2
public class MinioUtils {

    @Autowired
    MinioConfig prop;

    @Resource
    private MinioClient minioClient;

    @SneakyThrows(Exception.class)
    private void createBucket(String bucketName) {

    }

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
     * @param file 文件
     * @return 唯一文件URL
     */
    public String uploadFile(MultipartFile file) {
        String result;
        String originalFilename = file.getOriginalFilename();
        if (StringUtils.isBlank(originalFilename)) {
            throw new RuntimeException("文件名不能为空");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String objectName = formatter.format(LocalDateTime.now()) + "/" + System.currentTimeMillis()
                + "_" + UUID.randomUUID().toString().replace("-", "") + "_" + originalFilename;
        try {

            PutObjectArgs putObjectArgs = PutObjectArgs.builder().bucket(prop.getBucketName()).object(objectName)
                    .stream(file.getInputStream(), file.getSize(), -1).contentType(file.getContentType()).build();
            minioClient.putObject(putObjectArgs);
            result = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(prop.getBucketName())
                    .object(objectName)
                    .expiry(60 * 60)
                    .build());

        } catch (Exception e) {
            log.error(e);
            result = "文件上传失败";
        }
        return result;
    }
}
