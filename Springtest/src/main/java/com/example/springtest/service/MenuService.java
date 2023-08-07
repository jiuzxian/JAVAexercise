package com.example.springtest.service;

import com.example.springtest.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lin
 * @since 2023-07-28
 */
public interface MenuService extends IService<Menu> {
    //TODO 注释
    Map<Integer, Map> getIdNameMap();

}
