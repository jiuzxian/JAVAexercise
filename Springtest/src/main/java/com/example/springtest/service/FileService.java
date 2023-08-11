package com.example.springtest.service;

import com.example.springtest.entity.FileLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springtest.entity.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lin
 * @since 2023-08-11
 */
public interface FileService extends IService<FileLog> {

    Result upload(MultipartFile uploadFile, HttpServletRequest request);

    Result download(HttpServletResponse response, int fileId);
}
