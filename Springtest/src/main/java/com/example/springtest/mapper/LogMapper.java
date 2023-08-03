package com.example.springtest.mapper;

import com.example.springtest.entity.Log;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springtest.entity.Result;
import com.example.springtest.vo.InAuthVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lin
 * @since 2023-08-03
 */
@Mapper
public interface LogMapper extends BaseMapper<Log> {

}
