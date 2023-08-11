package com.example.springtest.controller;

import com.example.springtest.entity.Employees;
import com.example.springtest.mapper.EmployeesMapper;
import com.example.springtest.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
//定位网页
public class GetController {

    @Resource
    TokenUtil tokenUtil;

    @Resource
    EmployeesMapper employeesMapper;

    //登录页
    @GetMapping({"/", "/login"})
    public String toLogin() {
        return "Login";
    }

    @GetMapping({"/files", "/files.login"})
    public String toFiles() {
        return "files";
    }

    //主页
    @GetMapping({"/test", "/test.html"})
    public String all(Model model) {
        Map<String, Object> response = new HashMap<>();

        List<Employees> employeesList = employeesMapper.all();
        if (employeesList.isEmpty()) {
            model.addAttribute("status", 222);
        } else {
            model.addAttribute("status", 200);
            model.addAttribute("employees", employeesList);
        }
        ;
        return "test";
    }

    //注销登录
    @GetMapping("/logout")
    public String logout( HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        tokenUtil.logout(token);
        return "Login";
    }


}
