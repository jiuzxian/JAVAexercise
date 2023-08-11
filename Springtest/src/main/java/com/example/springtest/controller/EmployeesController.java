package com.example.springtest.controller;


import com.example.springtest.entity.Employees;
import com.example.springtest.entity.Result;
import com.example.springtest.service.EmployeesService;
import com.example.springtest.util.TokenUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;



@RestController
public class EmployeesController {



    @Resource
    private EmployeesService employeesService;

    @Resource
    private TokenUtil tokenUtil;


    // 方法注释
    // 请求参数和响应参数不推荐使用Map,响应参数用Result类
    /**
     * 用id或姓名搜索
     *
     * @param
     * @return
     */
    @PostMapping("/search")
    public Result<List<Employees>> search(@RequestParam("parameter") String parameter, HttpServletRequest httpServletRequest) {


        // 在项目中controler层不应该直接操作数据库，应该通过service层进行操作
        List<Employees> employeesList = employeesService.findBy(parameter);
        // 对集合的判空，可以考虑使用库中封装好的工具类
        // 单元测试中每个if、else都要覆盖都
        if (CollectionUtils.isEmpty(employeesList)) {
            return Result.fail(101, "未找到该员工！");
        }
        // if/else后面的{}不要省略
        else {
            return Result.success(employeesList);
        }

    }


    /**
     * 根据id更新
     *
     * @param e
     * @return
     */
    // 尽量只使用get/post两种请求方式
    @PostMapping("/update")
    public Result update(@RequestBody Employees e, HttpServletRequest httpServletRequest) {


        if (ObjectUtils.isEmpty(e)) {
            return Result.fail(102, "请输入员工信息！");
        } else {
            //判断操作人
            int userId=Integer.valueOf(String.valueOf(httpServletRequest.getAttribute("userId")));
            //更新人
            e.setUpdatedBy(userId);
            employeesService.update(e);
            return Result.success();
        }

    }

    /**
     * 增加
     *
     * @param e
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Employees e, HttpServletRequest httpServletRequest) {

        if (ObjectUtils.isEmpty(e)) {
            return Result.fail(102, "请输入员工信息！");
        } else {
            //判断操作人
            int userId=Integer.valueOf(String.valueOf(httpServletRequest.getAttribute("userId")));
            //创建人
            e.setCreatedBy(userId);
            employeesService.add(e);
            return Result.success();
        }

    }

    /**
     * 物理删除
     *
     * @param
     * @return
     */

    @PostMapping("/delete")
    public Result delete(@RequestParam("id") int id, HttpServletRequest httpServletRequest) {
        employeesService.deleteByID(id);
        return Result.success();
    }

    /**
     * 逻辑删除
     *
     * @param
     * @return
     */
    @PostMapping("/isDelete")
    public Result isDelete(@RequestParam("id") int id, HttpServletRequest httpServletRequest) {
        employeesService.isDelete(id);
        return Result.success();
    }


}
