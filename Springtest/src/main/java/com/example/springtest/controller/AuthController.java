package com.example.springtest.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springtest.annotation.AopLogger;
import com.example.springtest.entity.*;
import com.example.springtest.service.*;
import com.example.springtest.util.JWTUtil;
import com.example.springtest.util.TokenUtil;
import com.example.springtest.vo.AuthVo;
import com.example.springtest.vo.InAuthVo;
import com.example.springtest.vo.SettingVo;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;


@RestController
public class AuthController {
    // 全局对象无特殊要求外，都用private关键字修饰
    @Resource
    private EmployeesService employeesService;

    @Resource
    private AuthService authService;

    @Resource
    private SettingService settingService;


    @Resource
    private TokenUtil tokenUtil;

    /**
     * 查询员工的权限
     *
     * @param id
     * @return
     */
    @AopLogger("查询用户权限")
    @PostMapping("/authSearch")
    public Result<List<AuthVo>> search(@RequestParam("employeeId") Integer id) {

        //刷新token
//        // TODO 刷新token放在拦截器，验证完之后刷新
//        String token = httpServletRequest.getHeader("token");
//        //System.out.println("user"+httpServletRequest.getAttribute("userId"));
//        tokenUtil.freshToken(token);

        //根据id查权限
        // TODO 业务处理不放在controller层
        List<Auth> authList = authService.findByUId(id);

        // 若是判断是否有员工，应该放在方法第一行
        if (CollectionUtils.isEmpty(authList)) {
            return Result.fail(101, "未找到该员工！");
        }// 排版
        else {

            List<AuthVo> authVoList = new ArrayList<>();
            // TODO 为什么选择用map
            Map<Integer, Map> snmap = settingService.getIdNameMap();
            for (int i = 0; i < authList.size(); i++) {
                AuthVo vo = new AuthVo();
                Auth auth = authList.get(i);
                int settingId = auth.getSettingId();
                vo.setId(settingId);
                vo.setName(String.valueOf(snmap.get(settingId).get("object")));
                authVoList.add(vo);
            }

            List<AuthVo> authAllVoList=authService.upShow(authVoList);




//            List<AuthVo> authVoList = new ArrayList<>();
//            Map<Integer, Map> snmap = settingService.getIdNameMap();
//            Map<Integer, Map> smmap = settingService.getParentIdMap();
//            Map<Integer, Map> mnmap = menuService.getIdNameMap();
//
//            // 代码很长的时候不用foreach了，出bug了比较难定位
//            for (int i = 0; i < authList.size(); i++) {
//                AuthVo vo = new AuthVo();
//                Auth auth = authList.get(i);
//                //BeanUtils.copyProperties(auth, vo);
//                int settingId = auth.getSettingId();
//
//                //二级菜单名
//                String settingName = "";
//                try {
//                    // 基础类型不要用强转
//                    settingName = String.valueOf(snmap.get(settingId).get("object"));
//                } catch (Exception e) {
//                    settingName = "";
//                }
//                vo.setSettingName(settingName);
//
//                //一极菜单id
//                int menuId;
//                menuId = Integer.valueOf(String.valueOf(smmap.get(settingId).get("parent")));
//                vo.setParentId(menuId);
//
//                //一级菜单名
//                String menuName = "";
//                try {
//                    menuName = String.valueOf(mnmap.get(menuId).get("object"));
//                } catch (Exception e) {
//                    menuName = "";
//                }
//                vo.setParentName(menuName);
//                authVoList.add(vo);

            return Result.success(authAllVoList);
        }

    }

    /**
     * 修改员工权限
     *
     * @param vo
     * @return
     */
    @PostMapping("/authGive")
    public Result add(@RequestBody InAuthVo vo, HttpServletRequest httpServletRequest) {

        //判断操作人
//        String token = httpServletRequest.getHeader("token");
//        tokenUtil.freshToken(token);
//        // TODO 尝试使用过滤器的方式将登录的用户信息传递到接口，而不是每次使用的时候再次解析token
//        int userId = tokenUtil.getId(token);
        int userId=Integer.valueOf(String.valueOf(httpServletRequest.getAttribute("userId")));
        return authService.authGive(vo,userId);


    }

}
