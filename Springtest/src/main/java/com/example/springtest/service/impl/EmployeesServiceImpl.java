package com.example.springtest.service.impl;

import com.example.springtest.entity.Employees;
import com.example.springtest.mapper.EmployeesMapper;
import com.example.springtest.service.EmployeesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lin
 * @since 2023-07-20
 */
@Service
public class EmployeesServiceImpl extends ServiceImpl<EmployeesMapper, Employees> implements EmployeesService {

    @Resource
    EmployeesMapper employeesMapper;
    @Override
    public List<Employees> findBy(String parameter) {
        return employeesMapper.findBy(parameter);
    }

    @Override
    public void update(Employees e) {
        employeesMapper.update(e);
    }

    @Override
    public void add(Employees e) {
        employeesMapper.add(e);
    }

    @Override
    public void deleteByID(Integer id) {
        employeesMapper.deleteByID(id);
    }

    @Override
    public void isDelete(Integer id) {
        employeesMapper.isDelete(id);
    }


}
