package com.example.springtest.service.impl;

import com.example.springtest.entity.Departments;
import com.example.springtest.mapper.DepartmentsMapper;
import com.example.springtest.service.DepartmentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
