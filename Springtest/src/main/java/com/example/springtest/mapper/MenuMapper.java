package com.example.springtest.mapper;

import com.example.springtest.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface MenuMapper extends BaseMapper<Menu> {

    //映射所有id和name
    @MapKey("id")
    Map<Integer, Map> getIdNameMap();

}
