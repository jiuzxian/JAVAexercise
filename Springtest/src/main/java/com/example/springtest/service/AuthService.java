package com.example.springtest.service;

import com.example.springtest.entity.Auth;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lin
 * @since 2023-07-28
 */
public interface AuthService extends IService<Auth> {

    List<Auth> findByUId(int id);

    void removeByUId(int id);

}
