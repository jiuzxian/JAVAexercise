package com.example.springtest;

import com.example.springtest.service.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class Testredis {

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    private TokenService tokenService;

    @Test
    public void read(){
        System.out.println(redisTemplate.opsForValue().get("111"));
        redisTemplate.opsForValue().set("你好a","nihao");

    }

    @Test
    public void token(){


        String id ="15";
        String token;

        if(redisTemplate.hasKey(id)){
            if (redisTemplate.getExpire(id, TimeUnit.MINUTES)<=15){
                token= UUID.randomUUID().toString();
                redisTemplate.opsForValue().set(id, token,30, TimeUnit.MINUTES);
            }
            else{
               token= redisTemplate.opsForValue().get(id).toString();
            }
        }else{
            token= UUID.randomUUID().toString();
            redisTemplate.opsForValue().set(id, token,30, TimeUnit.MINUTES);
            System.out.println(token);
        }
    }
}
