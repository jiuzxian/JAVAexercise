package com.example.springtest.service.impl;

import com.example.springtest.entity.Salaries;
import com.example.springtest.mapper.SalariesMapper;
import com.example.springtest.service.SalariesService;
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
public class SalariesServiceImpl extends ServiceImpl<SalariesMapper, Salaries> implements SalariesService {

}
