package com.example.springtest.service;

import com.example.springtest.entity.Employees;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lin
 * @since 2023-07-20
 */
public interface EmployeesService extends IService<Employees> {

    List<Employees> findBy(String parameter);

    void update(Employees e);

    void add(Employees e);

    void deleteByID(Integer id);

    void isDelete(Integer id);
}
