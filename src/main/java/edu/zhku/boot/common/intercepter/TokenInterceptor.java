package edu.zhku.boot.common.intercepter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MJX
 * @date 2021/11/03
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())){
            return true;
        }
        boolean flag;
        try {
            String token = request.getHeader("Authorization");
            System.out.println(token);
            JWTVerifier verifier = JWT.require(Algorithm.HMAC512("123456")).build();
            DecodedJWT verify = verifier.verify(token);
            flag= true;
        } catch (Exception e) {
            throw new RuntimeException("没有权限");
        }
        return flag;
    }
}
