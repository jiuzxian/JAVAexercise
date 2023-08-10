package com.example.springtest.mapper;

import com.example.springtest.entity.Auth;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springtest.vo.AuthVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *     权限 Mapper这里可以说明是哪张表的接口
 *  Mapper 接口
 * </p>
 *
 * @author Lin
 * @since 2023-07-28
 */
@Mapper
public interface AuthMapper extends BaseMapper<Auth> {

    List<AuthVo> getMenuHierarchy(int userId);

}
