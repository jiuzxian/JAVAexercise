package com.example.springtest.service.impl;

import com.example.springtest.entity.FileLog;
import com.example.springtest.mapper.FileMapper;
import com.example.springtest.service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lin
 * @since 2023-08-11
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, FileLog> implements FileService {

}
