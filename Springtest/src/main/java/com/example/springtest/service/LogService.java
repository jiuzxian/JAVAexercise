package com.example.springtest.service;

import com.example.springtest.entity.Log;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  日志服务类
 * </p>
 *
 * @author Lin
 * @since 2023-08-03
 */
public interface LogService extends IService<Log> {

    void logStatus(String type, int userId, Object vo, int success);


}
