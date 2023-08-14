package com.example.springtest.controller;


import com.example.springtest.entity.FileLog;
import com.example.springtest.entity.Result;
import com.example.springtest.service.FileService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class FileController {
    //TODO
    @Resource
    FileService fileService;

    //日期格式化对象
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    /**
     * 上传文件
     * @param uploadFile
     * @param request
     * @return
     */

    @PostMapping("/upload")
    public Result upload(MultipartFile uploadFile, HttpServletRequest request) {
        return fileService.upload(uploadFile,request);
    }


    /**
     * 下载文件
     * @param response
     * @param fileId
     * @return
     * @throws IOException
     */
    @PostMapping("/download/{fileId}")
    public Result download(HttpServletResponse response, @PathVariable Integer fileId) throws IOException {
        return fileService.download(response,fileId);
    }








}
