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
    List<Employees> all();
    //TODO 传参尽量完整，需要具备一定的可读性和实体类的关联性
    List<Employees> findBy(@Param("par")String par);
    //TODO 参数名小驼峰
    void add(@Param("n")String Name, @Param("d")Integer DepartmentID, @Param("p")String Position, @Param("h")LocalDate HireDate);
    void deleteByID(@Param("id")Integer id);
    //void update(@Param("id")Integer id,@Param("n")String Name, @Param("d")Integer DepartmentID, @Param("p")String Position, @Param("h")LocalDate HireDate);
    void update(@Param("e")Employees e);

}
