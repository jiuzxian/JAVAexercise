package com.example.springtest.controller;

import com.example.springtest.entity.Result;
import com.example.springtest.entity.User;
import com.example.springtest.util.JWTUtil;
import com.example.springtest.service.UserService;

import com.example.springtest.util.TokenUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class LoginController {


    @Resource
    private UserService userService;

    @Resource
    public RedisTemplate redisTemplate;




    //登录
    @PostMapping("/loginPost")
    public Result getCode(@RequestParam("account")String account, @RequestParam("password")String password,HttpSession session) throws Exception {

        User user = userService.findByAccount(account);

        if (ObjectUtils.isEmpty(user)) {
            return Result.fail();
        }
        if (!password.equals(user.getPassword())) {
            return Result.fail(101, "密码错误！");
        }

        String jwtId=JWTUtil.createJwt(String.valueOf(user.getId()));


        String token= UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(token,jwtId,30, TimeUnit.MINUTES);
        session.setAttribute("token", token);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("token", token);

        return Result.success(result);

    }


}
