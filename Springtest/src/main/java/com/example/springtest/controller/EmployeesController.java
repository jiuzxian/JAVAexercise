package com.example.springtest.controller;

import com.example.springtest.config.LoginInterceptor;
import com.example.springtest.entity.Employees;
import com.example.springtest.mapper.EmployeesMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class EmployeesController {

    @Resource
    EmployeesMapper employeesMapper;

    @Resource
    private LoginInterceptor loginInterceptor;



    @PostMapping("/search")
    public Map<String,Object> search(@RequestBody Map<String,Object> map){
        Map<String, Object> response = new HashMap<>();
        String name=(String) map.get("name");
//        LambdaQueryWrapper<Employees>employeesLambdaQueryWrapper=new LambdaQueryWrapper<>();
//        String A="AA";
//        employeesLambdaQueryWrapper.like(Employees::getName,A);
//        List<Employees> employeesList2 =employeesService.list(employeesLambdaQueryWrapper);
//        System.out.println(employeesList2+"过滤");


        List<Employees> employeesList = employeesMapper.findByName(name);

        if(employeesList.isEmpty()){
            response.put("status",222);}
        else response.put("employees",employeesList);

        return response;
    }

    @PutMapping("/update")
    public Map<String,Object> update(@RequestBody Employees e){
        Map<String, Object> response = new HashMap<>();
        //Integer id=(Integer) map.get("id");
//        Employees e= employeesMapper.selectById(id);

//        ///测试用，前端改格式
//        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate h = LocalDate.parse((String)map.get("hireDate"), fmt);


        if(e==null){
            response.put("status",222);}
        else
        {response.put("status", 200);
//            e.setName((String) map.get("name"));
//            e.setDepartmentID((Integer) map.get("departmentID"));
//            e.setPosition((String) map.get("position"));
//            e.setHireDate(h);

            System.out.println(e);

            employeesMapper.update(e);}

        return response;
    }

    @PostMapping("/add")
    public Map<String,Object> add(@RequestBody Map<String,Object> map){
        Map<String, Object> response = new HashMap<>();
        String name = (String) map.get("name");
        Integer departmentID = (Integer) map.get("departmentID");
        String position = (String) map.get("position");

        ///测试用，前端改格式
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate h = LocalDate.parse((String)map.get("hireDate"), fmt);

        employeesMapper.add(name,departmentID,position,h);
        response.put("status", 200);
        return response;
    }

    @DeleteMapping("/delete")
    public Map<String,Object> delete(@RequestBody Map<String,Object> map){
        Map<String, Object> response = new HashMap<>();
        Integer id=(Integer) map.get("id");
        employeesMapper.deleteByID(id);
        response.put("status", 200);
        return response;
    }

}
