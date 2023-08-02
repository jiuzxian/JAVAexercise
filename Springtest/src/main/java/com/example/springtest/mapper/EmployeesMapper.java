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
    // TODO 方法上应该注释这个方法的作用或功能，用文档注释/** */，不是行注释

    /**
     * 初始页面加载所有员工
     * @return
     */
    List<Employees> all();

    /**
     * 根据id或姓名查找员工
     * @param parameter
     * @return
     */
    List<Employees> findBy(@Param("parameter")String parameter);

    /**
     * 增加员工
     * @param e
     * @return
     */
    int add(@Param("employee")Employees e);

    /**
     * 根据id物理删除员工
     * @param id
     * @return
     */
    int deleteByID(@Param("id")Integer id);

    /**
     * 根据id逻辑删除员工
     * @param id
     * @return
     */
    int isDelete(@Param("id") Integer id);

    /**
     * 更新
     * @param e
     * @return
     */
    int update(@Param("employee")Employees e);



}
