package com.example.springtest.service;

import com.example.springtest.entity.Auth;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springtest.entity.Result;
import com.example.springtest.vo.AuthVo;
import com.example.springtest.vo.InAuthVo;

import java.util.List;

/**
 * <p>
 *  权限服务类
 * </p>
 *
 * @author Lin
 * @since 2023-07-28
 */
public interface AuthService extends IService<Auth> {

    List<Auth> findByUId(int id);

    void removeByUId(int id);

    Result authGive(InAuthVo vo, int userId);

    //List<AuthVo> upShow(List<AuthVo> vo1s);

    List<AuthVo> getMenuHierarchy(int userId);

    List<AuthVo> upShow(List<AuthVo> vo1s);

}
