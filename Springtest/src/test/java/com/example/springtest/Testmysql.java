package com.example.springtest;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springtest.entity.Attendance;
import com.example.springtest.entity.Employees;
import com.example.springtest.entity.User;
import com.example.springtest.mapper.EmployeesMapper;
import com.example.springtest.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class Testmysql {

    @Resource
    DepartmentsService departmentsService;

    @Resource
    MenuService menuService;

    @Autowired
    UserService userService;
    @Resource
    EmployeesService employeesService;

    @Resource
    AttendanceService attendanceService;
    @Resource
    EmployeesMapper employeesMapper;

    @Test
    void userTest() {
        List<User> list = userService.list();
        System.out.println(list);
        System.out.println(userService.getById(5));
    }

    @Test
    void employeesTest() {
        List<Employees> list = employeesService.list();
        System.out.println(list);
//        System.out.println(employeesService.getById(5));
    }

    @Test
    void attendanceTest() {
        List<Attendance> list = attendanceService.list();
        System.out.println(list);
//        System.out.println(employeesService.getById(5));
    }

    @Test
    void findTestx() {

//        LambdaQueryWrapper<Employees>employeesLambdaQueryWrapper=new LambdaQueryWrapper<>();
//        String A="AA";
//        employeesLambdaQueryWrapper.like(Employees::getName,A);
//        List<Employees> employeesList2 =employeesService.list(employeesLambdaQueryWrapper);
//        System.out.println(employeesList2+"过滤");

        //navicat修改后要提交事务！！！
        //List<Employees> employeesList = employeesMapper.findBy("5");
        //System.out.println(employeesList);

        int a=1;


        Map<Integer,Map> mnmap = menuService.getIdNameMap();
        String settingName="";
        try {
            settingName=(String) mnmap.get(a).get("object");
        } catch (Exception e) {
            settingName="";
        }
        System.out.println(mnmap.get(a));




    }
    @Test
    void addx(){
//        Employees e =new Employees(null,"测试用",5,"医生", LocalDate.of(2020, 10, 15),0);
//        employeesMapper.add(e);
        //System.out.println(employeesMapper.add(e));
    }

    @Test
    void deletex(){
        System.out.println(employeesMapper.deleteByID(99));
    }

    @Test
    void updatex(){

        //employeesMapper.update(4,"测试用",5,"医生", LocalDate.of(2020, 10, 15));
        Employees e=employeesMapper.selectById(100);
        if(e==null){
            System.out.println("null");
        }
        else {
        e.setName("改名");
        employeesMapper.update(e);
    }
    }



}
