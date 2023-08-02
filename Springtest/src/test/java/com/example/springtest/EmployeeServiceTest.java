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

import java.sql.Timestamp;
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
        Employees e =new Employees(1, "John Doe", 101, "Manager", LocalDate.of(2023, 7, 31),
                0, new Timestamp(System.currentTimeMillis()), 1,
                new Timestamp(System.currentTimeMillis()), 2, "Some remarks");

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

    // 增加测试: 员工
    @Test
    public void testAddEmployee() {

        Employees e =new Employees(1, "John Doe", 101, "Manager", LocalDate.of(2023, 7, 31),
                0, new Timestamp(System.currentTimeMillis()), 1,
                new Timestamp(System.currentTimeMillis()), 2, "Some remarks");
        // 模拟方法返回 1
        when(employeesMapper.add(e)).thenReturn(1);
        // 调用测试方法
        int data = employeesServiceImpl.add(e);

        // 使用断言来验证查询结果是否与预期的数据相符
        Assert.assertEquals("数据不同", 1,data);

        // 验证方法是否被正确调用了一次
        Mockito.verify(employeesMapper).add(e);

        // 验证 mapper的其他方法是否没有被调用，以确保只验证了当前方法
        Mockito.verifyNoMoreInteractions(employeesMapper);
    }

    // 更新测试: 员工
    @Test
    public void testUpdateEmployee() {

        Employees e =new Employees(1, "John Doe", 101, "Manager", LocalDate.of(2023, 7, 31),
                0, new Timestamp(System.currentTimeMillis()), 1,
                new Timestamp(System.currentTimeMillis()), 2, "Some remarks");
        // 模拟方法返回 1
        when(employeesMapper.update(e)).thenReturn(1);
        // 调用测试方法
        int data = employeesServiceImpl.update(e);

        // 使用断言来验证查询结果是否与预期的数据相符
        Assert.assertEquals("数据不同", 1,data);

        // 验证方法是否被正确调用了一次
        Mockito.verify(employeesMapper).update(e);

        // 验证 mapper的其他方法是否没有被调用，以确保只验证了当前方法
        Mockito.verifyNoMoreInteractions(employeesMapper);
    }

    // 物理删除测试: 员工-有数据
    @Test
    public void testDeleteEmployee() {

        // 模拟方法返回1
        when(employeesMapper.deleteByID(1)).thenReturn(1);
        // 调用测试方法
        int data = employeesServiceImpl.deleteByID(1);

        // 使用断言来验证查询结果是否与预期的数据相符
        Assert.assertEquals("数据不同", 1,data);

        // 验证方法是否被正确调用了一次
        Mockito.verify(employeesMapper).deleteByID(1);

        // 验证 mapper的其他方法是否没有被调用，以确保只验证了当前方法
        Mockito.verifyNoMoreInteractions(employeesMapper);
    }

    // 物理删除测试: 员工-无数据
    @Test
    public void testDeleteEmployeeNoFind() {

        // 模拟方法返回1
        when(employeesMapper.deleteByID(100000)).thenReturn(0);
        // 调用测试方法
        int data = employeesServiceImpl.deleteByID(100000);

        // 使用断言来验证查询结果是否与预期的数据相符
        Assert.assertEquals("数据不同", 0,data);

        // 验证方法是否被正确调用了一次
        Mockito.verify(employeesMapper).deleteByID(100000);

        // 验证 mapper的其他方法是否没有被调用，以确保只验证了当前方法
        Mockito.verifyNoMoreInteractions(employeesMapper);
    }



    // 逻辑删除测试: 员工-有数据
    @Test
    public void testIsDeleteEmployee() {

        // 模拟方法返回1
        when(employeesMapper.isDelete(1)).thenReturn(1);
        // 调用测试方法
        int data = employeesServiceImpl.isDelete(1);

        // 使用断言来验证查询结果是否与预期的数据相符
        Assert.assertEquals("数据不同", 1,data);

        // 验证方法是否被正确调用了一次
        Mockito.verify(employeesMapper).isDelete(1);

        // 验证 mapper的其他方法是否没有被调用，以确保只验证了当前方法
        Mockito.verifyNoMoreInteractions(employeesMapper);
    }

    // 逻辑删除测试: 员工-无数据
    @Test
    public void testIsDeleteEmployeeNoFind() {

        // 模拟方法返回1
        when(employeesMapper.isDelete(100000)).thenReturn(0);
        // 调用测试方法
        int data = employeesServiceImpl.isDelete(100000);

        // 使用断言来验证查询结果是否与预期的数据相符
        Assert.assertEquals("数据不同", 0,data);

        // 验证方法是否被正确调用了一次
        Mockito.verify(employeesMapper).isDelete(100000);

        // 验证 mapper的其他方法是否没有被调用，以确保只验证了当前方法
        Mockito.verifyNoMoreInteractions(employeesMapper);
    }


}

