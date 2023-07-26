package com.example.springtest.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springtest.entity.Result;
import com.example.springtest.entity.User;
import com.example.springtest.mapper.UserMapper;
import com.example.springtest.service.UserService;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class LoginController {


    @Resource
    private UserService userService;

    //登录
    @PostMapping("/loginPost")
    public Result getCode(@RequestBody Map<String, Object> map, HttpSession session) throws Exception {
        String account = String.valueOf(map.get("account"));
        String password = String.valueOf(map.get("password"));

        User user = userService.findByAccount(account);


        if (ObjectUtils.isEmpty(user)) {
            return Result.fail();
        }
        if (!password.equals(user.getPassword())) {
            return Result.fail(101, "密码错误！");
        }
        session.setAttribute("user", user);
        return Result.success(user.getId());
    }


}
