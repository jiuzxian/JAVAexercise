package com.example.springtest.controller;

import com.example.springtest.config.LoginInterceptor;
import com.example.springtest.entity.Employees;
import com.example.springtest.mapper.EmployeesMapper;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Wrapper;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@RestController
public class EmployeesController {

    @Resource
    EmployeesMapper employeesMapper;



    //TODO 方法注释
    //TODO 请求参数和响应参数不推荐使用Map,响应参数用Result类

    /**
     *
     * @param map
     * @return
     */
    @PostMapping("/search")
    public Map<String, Object> search(@RequestBody Map<String, Object> map) {
        Map<String, Object> response = new HashMap<>();
        String par = (String) map.get("par");
//        LambdaQueryWrapper<Employees>employeesLambdaQueryWrapper=new LambdaQueryWrapper<>();
//        String A="AA";
//        employeesLambdaQueryWrapper.like(Employees::getName,A);
//        List<Employees> employeesList2 =employeesService.list(employeesLambdaQueryWrapper);
//        System.out.println(employeesList2+"过滤");

        //TODO 在项目中controoler层不应该直接操作数据库，应该通过service层进行操作
        List<Employees> employeesList = employeesMapper.findBy(par);
        //TODO 对集合的判空，可以考虑使用库中封装好的工具类
        if (employeesList.isEmpty()) {
            response.put("status", 222);
        }
        //TODO if/else后面的{}不要省略
        else {
            response.put("employees", employeesList);
        }

        return response;
    }
    //TODO　这是笔记吗？
    //注意：实体类字段要小驼峰！否则@Data生成的get或set无法识别（getName默认写法，n默认小写）
    //TODO 尽量只使用get/post两种请求方式
    @PutMapping("/update")
    public Map<String, Object> update(@RequestBody Employees e) {
        Map<String, Object> response = new HashMap<>();
        //Integer id=(Integer) map.get("id");
//        Employees e= employeesMapper.selectById(id);

//        ///测试用，前端改格式
//        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate h = LocalDate.parse((String)map.get("hireDate"), fmt);


        if (e == null) {
            response.put("status", 222);
        } else {
            response.put("status", 200);
//            e.setName((String) map.get("name"));
//            e.setDepartmentID((Integer) map.get("departmentID"));
//            e.setPosition((String) map.get("position"));
//            e.setHireDate(h);

            employeesMapper.update(e);
        }

        return response;
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody Map<String, Object> map) {
        Map<String, Object> response = new HashMap<>();
        //TODO 基础类型包装类和String 类型转换尽量不用这种强转
        String name = (String) map.get("name");
        String.valueOf(map.get("name"));
        Integer departmentID = (Integer) map.get("departmentID");
        String position = (String) map.get("position");

        ///测试用，前端改格式
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate h = LocalDate.parse((String) map.get("hireDate"), fmt);

        employeesMapper.add(name, departmentID, position, h);
        response.put("status", 200);
        return response;
    }


    @DeleteMapping("/delete")
    public Map<String, Object> delete(@RequestBody Map<String, Object> map) {
        Map<String, Object> response = new HashMap<>();
        Integer id = (Integer) map.get("id");
        //TODO 知道物理删除和逻辑删除吗？
        employeesMapper.deleteByID(id);
        response.put("status", 200);
        return response;
    }

}
