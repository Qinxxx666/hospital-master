package com.qin.hospital.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author WanYue
 * 生成Token工具类
 */
@Component
@Log4j2
public class JWTUtils {
    private final static String SECRET_KEY = "hospital";

    /**
     * 过期时间（单位：秒）
     */
    private final static Integer EXPIRES_AT = 1800;

    public static String getToken(Long userId, String userName) {
        String token = "";
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            token = JWT.create()
                    .withIssuer("auth0")    // 发布者
                    .withIssuedAt(new Date())   // 生成签名的时间
                    .withExpiresAt(DateUtils.addSeconds(new Date(), EXPIRES_AT))   // 生成签名的有效期,小时
                    .withClaim("userName", userName) // 插入数据
                    .withClaim("userId", userId)
                    .sign(algorithm);
            
        } catch (JWTCreationException e) {
            log.error(e);
            //如果Claim不能转换为JSON，或者在签名过程中使用的密钥无效，那么将会抛出JWTCreationException异常。
        }
        return token;
    }

    /**
     * 验证token  合法性
     */
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token);
    }
}
