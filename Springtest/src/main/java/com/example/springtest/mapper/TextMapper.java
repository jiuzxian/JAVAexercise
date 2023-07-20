package com.example.springtest.mapper;

import com.example.springtest.entity.Text;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface TextMapper extends BaseMapper<Text> {

}
