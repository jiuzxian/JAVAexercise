package com.example.springtest.util;

import io.jsonwebtoken.Claims;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


//TODO 实现类放在impl包路径下，是通用的方法则放在工具类
@Component
public class TokenUtil {


    @Resource
    private RedisTemplate redisTemplate;


    public int getId(String token){
        //判断操作人
        String jwtId = String.valueOf(redisTemplate.opsForValue().get(token));
        Claims claims = JWTUtil.parseJwt(jwtId);
        int userId= Integer.valueOf(String.valueOf(claims.getSubject()));
        return userId;
    }


    public String freshToken(String token){
        String jwtId=String.valueOf(redisTemplate.opsForValue().get(token));
        redisTemplate.opsForValue().set(token,jwtId,30, TimeUnit.MINUTES);
        return token;
    }


    public void logout(String token){
        redisTemplate.delete(token);
    }



//    public String freshToken(String token){
//
//        String jwtId= UUID.randomUUID().toString();
//        redisTemplate.opsForValue().set(jwtId, token,30, TimeUnit.MINUTES);
//
//
////        if(redisTemplate.hasKey(id)){
////            if (redisTemplate.getExpire(id,TimeUnit.MINUTES)<=15){
////            String token= UUID.randomUUID().toString();
////            redisTemplate.opsForValue().set(id, token,30, TimeUnit.MINUTES);
////            return token;
////            }
////            else{
////                return redisTemplate.opsForValue().get(id).toString();
////            }
////        }else{
////            String token= UUID.randomUUID().toString();
////            redisTemplate.opsForValue().set(id, token,30, TimeUnit.MINUTES);
////            return token;
////        }
//
////    }


}
