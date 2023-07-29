package com.example.springtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Component
public class TokenService {

    @Resource
    public RedisTemplate redisTemplate;

//    public String newToken(String id){
//        String token= UUID.randomUUID().toString();
//        redisTemplate.opsForValue().set(id, token,30, TimeUnit.MINUTES);
//        return token;
//    }

    public String freshToken(String id){

        if(redisTemplate.hasKey(id)){
            if (redisTemplate.getExpire(id,TimeUnit.MINUTES)<=15){
            String token= UUID.randomUUID().toString();
            redisTemplate.opsForValue().set(id, token,30, TimeUnit.MINUTES);
            return token;
            }
            else{
                return redisTemplate.opsForValue().get(id).toString();
            }
        }else{
            String token= UUID.randomUUID().toString();
            redisTemplate.opsForValue().set(id, token,30, TimeUnit.MINUTES);
            return token;
        }

    }


}
