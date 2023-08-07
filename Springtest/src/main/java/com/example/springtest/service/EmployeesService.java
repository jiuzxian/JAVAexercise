package com.example.springtest.service;

import com.example.springtest.entity.Employees;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  员工服务类
 * </p>
 *
 * @author Lin
 * @since 2023-07-20
 */
public interface EmployeesService extends IService<Employees> {

    List<Employees> findBy(String parameter);

    int update(Employees e);

    int add(Employees e);

    int deleteByID(Integer id);

    int isDelete(Integer id);
}
