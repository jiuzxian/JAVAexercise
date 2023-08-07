package com.example.springtest.service.impl;

import com.example.springtest.entity.Menu;
import com.example.springtest.entity.Setting;
import com.example.springtest.mapper.MenuMapper;
import com.example.springtest.mapper.SettingMapper;
import com.example.springtest.service.SettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 *  服务实现类,TODO 哪个业务功能的实现类
 * </p>
 *
 * @author Lin
 * @since 2023-07-28
 */
@Service
public class SettingServiceImpl extends ServiceImpl<SettingMapper, Setting> implements SettingService {

    @Resource
    SettingMapper settingMapper;

    @Override
    public Map<Integer, Map> getIdNameMap(){
        return settingMapper.getIdNameMap();
    }

    @Override
    public Map<Integer, Map> getParentIdMap(){
        return settingMapper.getParentIdMap();
    }

}
