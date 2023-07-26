package com.example.springtest;

import com.example.springtest.entity.Employees;
import com.example.springtest.mapper.EmployeesMapper;
import com.example.springtest.service.impl.EmployeesServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import static org.mockito.Mockito.when;

@Transactional
@Rollback
@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    // 定义静态常量
    private static final String RESOURCE_PATH = "EmployeeServiceTest/";

    // 模拟对象
    @Mock
    private EmployeesMapper employeesMapper;

    // 定义测试(待被注入Inject)对象
    @InjectMocks
    private EmployeesServiceImpl employeesServiceImpl;


    // 查询测试: 员工-无数据
    @Test
    public void testSearchEmployeeEmpty() {

        List<Employees> expected= new ArrayList<>();

        // 模拟 mapper.findBy 方法返回 null
        when(employeesMapper.findBy("5")).thenReturn(expected);

        // 调用测试方法
        List<Employees> data = employeesServiceImpl.findBy("5");

        // 使用断言来验证查询结果是否与预期的数据相符
        Assert.assertEquals("数据应为空", 0,data.size());

        // 验证方法是否被正确调用了一次
        Mockito.verify(employeesMapper).findBy("5");

        // 验证 mapper的其他方法是否没有被调用，以确保只验证了当前方法
        Mockito.verifyNoMoreInteractions(employeesMapper);
    }

    // 查询测试: 员工-有数据
    @Test
    public void testSearchEmployeeNonEmpty() {

        List<Employees> expected= new ArrayList<>();
        Employees e =new Employees(4,"测试用",5,"医生", LocalDate.of(2020, 10, 15),0);
        expected.add(e);
        // 模拟 mapper.findBy 方法返回 null
        when(employeesMapper.findBy("5")).thenReturn(expected);
        // 调用测试方法
        List<Employees> data = employeesServiceImpl.findBy("5");

        // 使用断言来验证查询结果是否与预期的数据相符
        Assert.assertEquals("数据不同", expected,data);

        // 验证方法是否被正确调用了一次
        Mockito.verify(employeesMapper).findBy("5");

        // 验证 mapper的其他方法是否没有被调用，以确保只验证了当前方法
        Mockito.verifyNoMoreInteractions(employeesMapper);
    }

    // 增加测试: 员工-有数据
    @Test
    public void testAddEmployee() {

        List<Employees> expected= new ArrayList<>();
        Employees e =new Employees(4,"测试用",5,"医生", LocalDate.of(2020, 10, 15),0);
        expected.add(e);
        // 模拟 mapper.findBy 方法返回 null
        when(employeesMapper.findBy("5")).thenReturn(expected);
        // 调用测试方法
        List<Employees> data = employeesServiceImpl.findBy("5");

        // 使用断言来验证查询结果是否与预期的数据相符
        Assert.assertEquals("数据不同", expected,data);

        // 验证方法是否被正确调用了一次
        Mockito.verify(employeesMapper).findBy("5");

        // 验证 mapper的其他方法是否没有被调用，以确保只验证了当前方法
        Mockito.verifyNoMoreInteractions(employeesMapper);
    }




    // 测试: 查询用户-有数据
//    @Test
//    public void testQueryUserWithData() {
//        // 模拟依赖方法
//        String path = RESOURCE_PATH + "testQueryUserWithData/";
//        // 模拟依赖方法: userDAO.countByCompany
//        Long companyId = 123L;
//        // 使用 Mockito.doReturn() 模拟 userDAO.countByCompany 方法返回 91L
//        Mockito.doReturn(91L).when(userDAO).countByCompany(companyId);
//
//        // 模拟依赖方法: userDAO.queryByCompany
//        Long startIndex = 90L;
//        Integer pageSize = 10;
//        // 从测试数据文件中读取数据列表，并转换成 List<UserVO> 对象
//        String text = ResourceHelper.getResourceAsString(getClass(), path + "dataList.json");
//        List<UserVO> dataList = JSON.parseArray(text, UserVO.class);
//        // 使用 Mockito.doReturn() 模拟 userDAO.queryByCompany 方法返回数据列表
//        Mockito.doReturn(dataList).when(userDAO).queryByCompany(companyId, startIndex, pageSize);
//
//        // 调用测试方法
//        // 调用 UserService 的 queryUser 方法
//        PageDataVO<UserVO> pageData = userService.queryUser(companyId, startIndex, pageSize);
//        // 读取测试数据文件，此处是 json 文件，然后将其转换成字符串
//        text = ResourceHelper.getResourceAsString(getClass(), path + "pageData.json");
//        // 使用断言来验证查询结果是否与预期的数据相符
//        Assert.assertEquals("分页数据不一致", text, JSON.toJSONString(pageData));
//
//        // 验证依赖方法
//        // 验证依赖方法: userDAO.countByCompany
//        // 验证 userDAO.countByCompany 方法是否被正确调用了一次
//        Mockito.verify(userDAO).countByCompany(companyId);
//
//        // 验证依赖方法: userDAO.queryByCompany
//        // 验证 userDAO.queryByCompany 方法是否被正确调用了一次
//        Mockito.verify(userDAO).queryByCompany(companyId, startIndex, pageSize);
//    }
}

