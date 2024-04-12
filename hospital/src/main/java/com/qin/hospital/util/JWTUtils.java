package com.qin.hospital.util;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author WanYue
 * 生成Token工具类
 */
public class JWTUtils {

    @Value("${token.secretKey}")
    private String secretKey;
    public static String getToken(String userId, String userName) {

       String token ;

    return "";
    }

}
