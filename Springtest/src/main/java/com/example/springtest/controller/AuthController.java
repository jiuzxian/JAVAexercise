package com.example.springtest.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springtest.entity.*;
import com.example.springtest.service.*;
import com.example.springtest.util.JWTUtil;
import com.example.springtest.vo.AuthVo;
import com.example.springtest.vo.InAuthVo;
import com.example.springtest.vo.SettingVo;
import io.jsonwebtoken.Claims;
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
    //TODO 全局对象无特殊要求外，都用private关键字修饰
    @Resource
    private EmployeesService employeesService;

    @Resource
    private AuthService authService;

    @Resource
    private SettingService settingService;

    @Resource
    private MenuService menuService;

    @Resource
    private DepartmentsService departmentsService;

    @Resource
    public RedisTemplate redisTemplate;

    //TODO 方法注释

    /**
     * 查询员工的权限
     *
     * @param id
     * @return
     */
    @PostMapping("/authSearch")
    //TODO 入参不用基础类型,用包装类型
    //TODO 前端要怎么使用这个数据结构
    public Result<List<AuthVo>> search(@RequestParam("userId") Integer id) {


        //根据id查权限
        //TODO 业务处理不放在controller层
        List<Auth> authList = authService.findByUId(id);

        //TODO 若是判断是否有员工，应该放在方法第一行
        if (CollectionUtils.isEmpty(authList)) {
            return Result.fail(101, "未找到该员工！");
        }//TODO 排版
        else {

            List<AuthVo> authVoList = new ArrayList<>();
            Map<Integer, Map> snmap = settingService.getIdNameMap();
            Map<Integer, Map> smmap = settingService.getParentIdMap();
            Map<Integer, Map> mnmap = menuService.getIdNameMap();

            //TODO 代码很长的时候不用foreach了，出bug了比较难定位
            for (int i = 0; i < authList.size(); i++) {
                AuthVo vo = new AuthVo();
                Auth auth = authList.get(i);
                BeanUtils.copyProperties(auth, vo);
                int settingId = auth.getSettingId();

                //二级菜单名
                String settingName = "";
                try {
                    //TODO 基础类型不要用强转
                    settingName = String.valueOf(snmap.get(settingId).get("object"));
                } catch (Exception e) {
                    settingName = "";
                }
                vo.setSettingName(settingName);

                //一极菜单id
                int menuId;
                menuId = Integer.valueOf(String.valueOf(smmap.get(settingId).get("parent")));
                vo.setParentId(menuId);

                //一级菜单名
                String menuName = "";
                try {
                    menuName = String.valueOf(mnmap.get(menuId).get("object"));
                } catch (Exception e) {
                    menuName = "";
                }
                vo.setParentName(menuName);
                authVoList.add(vo);
            }


            return Result.success(authVoList);
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
        String token = httpServletRequest.getHeader("token");
        String jwtId = String.valueOf(redisTemplate.opsForValue().get(token));
        Claims claims = JWTUtil.parseJwt(jwtId);
        int userId= Integer.valueOf(String.valueOf(claims.getSubject()));

        int id = vo.getUserId();
        List<Integer> list = vo.getList();
        //把现有的都删了
        try {
            authService.removeByUId(id);
        } catch (Exception e) {
        }

        //一个个存
        //TODO 排版 ctrl + alt + l
        //TODO 为什么使用foreach遍历？
        //TODO 新增数据时，创建人、创建时间也要进行更新

        for (int i = 0; i < list.size(); i++) {
            int n = list.get(i);
            Auth auth = new Auth();
            auth.setUserId(id);
            auth.setSettingId(n);
            //创建人同步更新
            auth.setCreatedBy(userId);
            auth.setUpdatedBy(userId);
            authService.save(auth);
        }

        //TODO result类返回成功时，一般不重设code编码
        return Result.success("授权成功！");

    }

}
