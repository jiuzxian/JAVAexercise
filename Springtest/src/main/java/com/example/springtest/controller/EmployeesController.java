package com.example.springtest.controller;


import com.example.springtest.entity.Employees;
import com.example.springtest.entity.Result;
import com.example.springtest.service.EmployeesService;
import com.example.springtest.service.TokenService;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;


@RestController
public class EmployeesController {


    @Resource
    EmployeesService employeesService;
    @Resource
    private TokenService tokenService;


    // 方法注释
    // 请求参数和响应参数不推荐使用Map,响应参数用Result类
    /**
     * 用id或姓名搜索
     *
     * @param
     * @return
     */
    @PostMapping("/search")
    public Result<List<Employees>> search(@RequestParam("parameter") String parameter) {


        // 在项目中controoler层不应该直接操作数据库，应该通过service层进行操作
        List<Employees> employeesList = employeesService.findBy(parameter);
        // 对集合的判空，可以考虑使用库中封装好的工具类
        //TODO 单元测试中每个if、else都要覆盖都
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
    public Result update(@RequestBody Employees e) {

//        ///测试用，前端改格式
//        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate h = LocalDate.parse((String)map.get("hireDate"), fmt);

        if (ObjectUtils.isEmpty(e)) {
            return Result.fail(102, "请输入员工信息！");
        } else {
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
    public Result add(@RequestBody Employees e) {
        // 基础类型包装类和String 类型转换尽量不用这种强转
//        String name = (String) map.get("name");
//        String.valueOf(map.get("name"));

        if (ObjectUtils.isEmpty(e)) {
            return Result.fail(102, "请输入员工信息！");
        } else {
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
    public Result delete(@RequestParam("id") int id) {
        // 物理删除和逻辑删除
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
    public Result isDelete(@RequestParam("id") int id) {
        employeesService.isDelete(id);
        return Result.success();
    }


}
