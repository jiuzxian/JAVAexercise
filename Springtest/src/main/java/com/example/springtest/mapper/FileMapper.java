package com.example.springtest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springtest.entity.FileLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lin
 * @since 2023-07-20
 */
@Mapper
public interface FileMapper extends BaseMapper<FileLog> {

}
