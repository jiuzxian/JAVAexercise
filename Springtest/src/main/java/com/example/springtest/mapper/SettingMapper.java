package com.example.springtest.mapper;

import com.example.springtest.entity.Setting;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springtest.vo.AuthVo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lin
 * @since 2023-07-28
 */
@Mapper
public interface SettingMapper extends BaseMapper<Setting> {

    //映射所有id和name
    @MapKey("id")
    Map<Integer, Map> getIdNameMap();
    //映射所有id和parentid
    @MapKey("id")
    Map<Integer, Map> getParentIdMap();

    AuthVo getPbyS(int settingId);

}
