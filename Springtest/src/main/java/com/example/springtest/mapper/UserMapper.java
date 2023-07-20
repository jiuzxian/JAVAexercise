package com.example.springtest.mapper;

import com.example.springtest.entity.Employees;
import com.example.springtest.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lin
 * @since 2023-07-20
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User findByAccount(@Param("account")String account);
}
