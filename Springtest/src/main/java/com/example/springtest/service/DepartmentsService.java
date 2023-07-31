package com.example.springtest.service;

import com.example.springtest.entity.Departments;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lin
 * @since 2023-07-20
 */
public interface DepartmentsService extends IService<Departments> {

    Map<Integer,Map> getIdNameMap();

}
