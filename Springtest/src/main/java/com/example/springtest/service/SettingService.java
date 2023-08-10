package com.example.springtest.service;

import com.example.springtest.entity.Setting;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springtest.vo.AuthVo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lin
 * @since 2023-07-28
 */
public interface SettingService extends IService<Setting> {

    Map<Integer, Map> getIdNameMap();
    Map<Integer, Map> getParentIdMap();


    AuthVo getPbyS(int settingId);
}
