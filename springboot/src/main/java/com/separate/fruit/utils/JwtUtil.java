package com.separate.fruit.utils;

import java.util.Date;

import com.separate.fruit.entity.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

    private static long time = 1000 * 60 * 30;//设置token过期时间
    private static String signature = "admin";//解密名

    /**
     * 设置token,验证用户
     *
     * @param users
     * @return
     */
    public static String createToken(Users users) {
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                //header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //paylod
                .claim("username", users.getUserName())
                .claim("phone", users.getPhone())
                .claim("mail", users.getUserEmail())
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis() + time))
                //signature
                .signWith(SignatureAlgorithm.HS256, signature)
                .compact();
        return jwtToken;
    }

    /**
     * 验证用户是否合法,合法则可以继续操作,不合法则退出系统
     *
     * @param token
     * @return
     */
    public static Boolean checkToken(String token) {
        if (token == null) {
            return false;
        }
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signature).parseClaimsJws(token);
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
        return true;
    }
}
