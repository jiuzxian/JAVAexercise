package com.example.springtest.service.impl;

import com.example.springtest.entity.Menu;
import com.example.springtest.mapper.DepartmentsMapper;
import com.example.springtest.mapper.MenuMapper;
import com.example.springtest.service.MenuService;
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
 * @since 2023-07-28
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    //关键字
    @Resource
    MenuMapper menuMapper;

    @Override
    public Map<Integer, Map> getIdNameMap(){
        return  menuMapper.getIdNameMap();
    }

}
