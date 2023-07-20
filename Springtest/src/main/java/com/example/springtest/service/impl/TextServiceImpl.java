package com.example.springtest.service.impl;

import com.example.springtest.entity.Text;
import com.example.springtest.mapper.TextMapper;
import com.example.springtest.service.TextService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lin
 * @since 2023-07-20
 */
@Service
public class TextServiceImpl extends ServiceImpl<TextMapper, Text> implements TextService {

}
