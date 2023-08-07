package com.example.springtest.service;

import com.example.springtest.entity.Log;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springtest.entity.Result;
import com.example.springtest.vo.InAuthVo;

/**
 * <p>
 *  日志服务类
 * </p>
 *
 * @author Lin
 * @since 2023-08-03
 */
public interface LogService extends IService<Log> {

    Result logSuccess(String type,int userId, Object vo);
    Result logFail(String type,int userId, Object vo);

}
