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
//TODO 类注释
@RestController
public class AuthController {
    //TODO 全局对象无特殊要求外，都用private关键字修饰
    @Resource
    EmployeesService employeesService;

    @Resource
    AuthService authService;

    @Resource
    SettingService settingService;

    @Resource
    MenuService menuService;

    //TODO 还没写完？
    //TODO 方法注释
    @PostMapping("/authSearch")
    //TODO 入参不用基础类型,用包装类型
    public Result<List<AuthVo>> search(@RequestParam("userId") int id) {

        //根据id查权限
        //TODO 业务处理不放在controller层
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

        //TODO 若是判断是否有员工，应该放在方法第一行
        if (CollectionUtils.isEmpty(authList)) {
            return Result.fail(101, "未找到该员工！");
        }//TODO 排版

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
        //TODO 排版
        //TODO 为什么使用foreach遍历？
        //TODO 新增数据时，创建人、创建时间也要进行更新
        list.forEach(n -> {
                    Auth auth = new Auth();
                    auth.setUserId(id);
                    auth.setMenuId(n);
                    authService.save(auth);
                });
        //TODO result类返回成功时，一般不重设code编码
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

        //TODO 得到查询结果后就应该进行判断，而不是处理完后再判断
        if (CollectionUtils.isEmpty(settingList)) {
            return Result.fail(101, "无子功能！");
        }

        else {
            return Result.success(settingVoList);
        }

    }




}
