package com.example.springtest.service.impl;

import com.example.springtest.entity.Departments;
import com.example.springtest.mapper.DepartmentsMapper;
import com.example.springtest.service.DepartmentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lin
 * @since 2023-07-20
 */
@Service
public class DepartmentsServiceImpl extends ServiceImpl<DepartmentsMapper, Departments> implements DepartmentsService {

    @Resource
    DepartmentsMapper departmentsMapper;

    @Override
    public Map<Integer,Map> getIdNameMap(){
        return  departmentsMapper.getIdNameMap();
    }

}
