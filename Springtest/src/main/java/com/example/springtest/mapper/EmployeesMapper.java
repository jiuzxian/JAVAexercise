package com.example.springtest.mapper;

import com.example.springtest.entity.Employees;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lin
 * @since 2023-07-20
 */
@Mapper
public interface EmployeesMapper extends BaseMapper<Employees> {
    //TODO 方法上应该注释这个方法的作用或功能
    //初始页面加载所有员工
    List<Employees> all();
    //TODO 传参尽量完整，需要具备一定的可读性和实体类的关联性
    //根据id或姓名查找员工
    List<Employees> findBy(@Param("parameter")String parameter);
    //TODO 参数名小驼峰
    //增加员工
    void add(@Param("employee")Employees e);
    //根据id物理删除员工
    int deleteByID(@Param("id")Integer id);
    //根据id逻辑删除员工
    int isDelete(@Param("id") Integer id);
    //更新
    int update(@Param("employee")Employees e);



}
