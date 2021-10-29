package edu.zhku.boot.util;

import cn.hutool.jwt.Claims;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author MJX
 * @date 2021/10/28
 */
public class JWTUtils {
    private static final long TOKEN_EXPIRATION = 24 * 60 * 60 * 1000;
    private static final String TOKEN_SIGN_KEY = "123456";

    public static String createToken(Long teacherId) {
        String token = JWT.create()
                .withSubject("student-achievement-management-system")
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .withClaim("teacherId", teacherId)
                .sign(Algorithm.HMAC512(TOKEN_SIGN_KEY));
        return token;
    }

    public static Long getUserId(String token) {
        if (!StringUtils.hasText(token)) {
            return null;
        }
        Long userId = JWT.decode(token).getClaim("teacherId").asLong();
        return userId;
    }

}

