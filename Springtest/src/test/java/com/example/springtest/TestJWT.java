package com.example.springtest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class TestJWT {

    // 这里用于演示的秘钥，实际使用中应该更加安全地保存
    private static final String SECRET_KEY = "your-secret-key";

    public static void main(String[] args) {
        // 创建JWT
        String jwt = createJwt("user123");
        System.out.println(jwt);

        // 解析和验证JWT
        if (validateJwt(jwt)) {
            System.out.println("JWT验证成功！");
            Claims claims = parseJwt(jwt);
            System.out.println("JWT中的主题：" + claims.getSubject());
            System.out.println("JWT中的过期时间：" + claims.getExpiration());
        } else {
            System.out.println("JWT验证失败！");
        }
    }

    // 创建JWT
    public static String createJwt(String subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000); // 设置JWT过期时间为1小时

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // 验证JWT
    public static boolean validateJwt(String jwt) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    // 解析JWT
    public static Claims parseJwt(String jwt) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt).getBody();
    }

}
