package com.example.springtest.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springtest.entity.*;
import com.example.springtest.service.*;
import com.example.springtest.vo.AuthVo;
import com.example.springtest.vo.InAuthVo;
import com.example.springtest.vo.SettingVo;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

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

    @Resource
    DepartmentsService departmentsService;

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


        Map<Integer,Map> snmap = settingService.getIdNameMap();
        Map<Integer,Map> smmap = settingService.getParentIdMap();
        Map<Integer,Map> mnmap = menuService.getIdNameMap();


        authList.forEach(auth -> {
            AuthVo vo = new AuthVo();
            BeanUtils.copyProperties(auth, vo);

            //表名还没改所以转一下，一会改
            int settingId=auth.getMenuId();
            vo.setSettingId(settingId);

            //二级菜单名
            String settingName="";
            try {
                settingName=(String) snmap.get(settingId).get("object");
            } catch (Exception e) {
                settingName="";
            }
            vo.setSettingName(settingName);

            //一极菜单id
            int menuId;
            menuId=(Integer) smmap.get(settingId).get("parent");
            vo.setParentId(menuId);

            //一级菜单名
            String menuName="";
            try {
                menuName=(String) mnmap.get(menuId).get("object");
            } catch (Exception e) {
                menuName="";
            }
            vo.setParentName(menuName);
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
        //把现有的都删了
        LambdaQueryWrapper<Auth> authLambdaQueryWrapper=new LambdaQueryWrapper<>();
        authLambdaQueryWrapper.eq(Auth::getUserId,id);
        authService.remove(authLambdaQueryWrapper);

        //一个个存
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






}
