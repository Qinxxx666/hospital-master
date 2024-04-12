package com.qin.hospital.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * @author WanYue
 * 生成Token工具类
 */
public class JWTUtils {

    @Value("${token.secretKey}")
    private String secretKey;
    public static String getToken(String userId, String userName) {
        try{
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create()
                    .withIssuer("auth0")    // 发布者
                    .withIssuedAt(new Date())   // 生成签名的时间
                    .withExpiresAt(DateUtils.addHours(new Date(),2))   // 生成签名的有效期,小时
                    .withClaim("name","wuyuwei") // 插入数据
                    .sign(algorithm);

            System.out.println(token);
        }catch(JWTCreationException e){
            e.printStackTrace();
            //如果Claim不能转换为JSON，或者在签名过程中使用的密钥无效，那么将会抛出JWTCreationException异常。
        }

    return "";
    }

}
