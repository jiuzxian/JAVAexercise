package com.example.springtest.mapper;

import com.example.springtest.entity.Departments;
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
 * @since 2023-07-20
 */
@Mapper
public interface DepartmentsMapper extends BaseMapper<Departments> {

    @MapKey("id")
    Map<Integer,Map> getIdNameMap();

}
