package com.example.springtest.service.impl;

import com.example.springtest.entity.Log;
import com.example.springtest.entity.Result;
import com.example.springtest.mapper.LogMapper;
import com.example.springtest.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * <p>
 *  日志服务实现类
 * </p>
 *
 * @author Lin
 * @since 2023-08-03
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

    @Resource
    LogService logService;

    @Override
    public Result logSuccess(String type, int userId, Object vo) {
        Log log= new Log();
        log.setType(type);
        log.setUserId(userId);
        log.setOperateAt( new Timestamp(System.currentTimeMillis()));
        log.setObject(vo.toString());
        log.setSuccessful(1);
        logService.save(log);
        return  Result.success("授权成功！");
    }

    @Override
    public Result logFail(String type, int userId, Object vo) {
        Log log= new Log();
        log.setType(type);
        log.setUserId(userId);
        log.setOperateAt( new Timestamp(System.currentTimeMillis()));
        log.setObject(vo.toString());
        log.setSuccessful(0);
        logService.save(log);
        return  Result.fail();
    }
}
