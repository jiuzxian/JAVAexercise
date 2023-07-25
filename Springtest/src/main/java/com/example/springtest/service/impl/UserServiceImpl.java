package com.example.springtest.service.impl;

import com.example.springtest.entity.User;
import com.example.springtest.mapper.UserMapper;
import com.example.springtest.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lin
 * @since 2023-07-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    UserMapper userMapper;
    @Override
    public User findByAccount(String account) {
        return userMapper.findByAccount(account);
    }
}
