package com.example.springtest.service.impl;

import com.example.springtest.entity.Log;
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
    public void logStatus(String type, int userId, Object vo, int success) {
        Log log= new Log();
        log.setType(type);
        log.setUserId(userId);
        log.setOperateAt( new Timestamp(System.currentTimeMillis()));
        //TODO 
        log.setObject(vo.toString());
        log.setSuccessful(success);
        logService.save(log);
    }


}
