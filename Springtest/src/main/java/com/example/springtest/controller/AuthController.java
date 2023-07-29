package com.example.springtest.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springtest.entity.*;
import com.example.springtest.service.AuthService;
import com.example.springtest.service.EmployeesService;
import com.example.springtest.service.MenuService;
import com.example.springtest.service.SettingService;
import com.example.springtest.vo.AuthVo;
import com.example.springtest.vo.InAuthVo;
import com.example.springtest.vo.SettingVo;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthController {

    @Resource
    EmployeesService employeesService;

    @Resource
    AuthService authService;

    @Resource
    SettingService settingService;

    @Resource
    MenuService menuService;


    @PostMapping("/authSearch")
    public Result<List<AuthVo>> search(@RequestParam("userId") int id) {

        //根据id查权限
        LambdaQueryWrapper<Auth> authLambdaQueryWrapper=new LambdaQueryWrapper<>();
        authLambdaQueryWrapper.eq(Auth::getUserId,id);
        List<Auth> authList =authService.list(authLambdaQueryWrapper);
        List<AuthVo> authVoList =new ArrayList<>();

        authList.forEach(auth -> {
            AuthVo vo = new AuthVo();
            BeanUtils.copyProperties(auth, vo);
            vo.setMenuName(menuService.getById(auth.getMenuId()).getObject());
            authVoList.add(vo);
        });


        if (CollectionUtils.isEmpty(authList)) {
            return Result.fail(101, "未找到该员工！");
        }

        else {
            return Result.success(authVoList);
        }

    }

    @PostMapping("/authGive")
    public Result add(@RequestBody InAuthVo vo) {

        int id = vo.getUserId();
        List<Integer> list =vo.getList();
        //检查是否已经授权过当前用户
        LambdaQueryWrapper<Auth> authLambdaQueryWrapper=new LambdaQueryWrapper<>();
        authLambdaQueryWrapper.eq(Auth::getUserId,id);
        authService.remove(authLambdaQueryWrapper);

        list.forEach(n -> {
                    Auth auth = new Auth();
                    auth.setUserId(id);
                    auth.setMenuId(n);
                    authService.save(auth);
                });

        return Result.success(200,"授权成功！");

        }

    @PostMapping("/authKids")
    public Result<List<SettingVo>> display(@RequestParam("menuId") int id) {

        //根据id查权限
        LambdaQueryWrapper<Setting> settingLambdaQueryWrapper=new LambdaQueryWrapper<>();
        settingLambdaQueryWrapper.eq(Setting::getParent,id);
        List<Setting> settingList =settingService.list(settingLambdaQueryWrapper);
        List<SettingVo> settingVoList =new ArrayList<>();

        settingList.forEach(setting -> {
            SettingVo vo = new SettingVo();
            BeanUtils.copyProperties(setting, vo);
            settingVoList.add(vo);
        });


        if (CollectionUtils.isEmpty(settingList)) {
            return Result.fail(101, "无子功能！");
        }

        else {
            return Result.success(settingVoList);
        }

    }




}
