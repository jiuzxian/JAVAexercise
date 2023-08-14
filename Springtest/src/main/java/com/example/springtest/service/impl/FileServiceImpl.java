package com.example.springtest.service.impl;

import com.example.springtest.entity.FileLog;
import com.example.springtest.entity.Result;
import com.example.springtest.mapper.FileMapper;
import com.example.springtest.service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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
    @Override
    public Result upload(MultipartFile uploadFile, HttpServletRequest request){

        //根据虚拟路径（"/uploadFile/"）获取这个虚拟路径在服务器文件系统上的实际物理路径
        String realPath = request.getSession().getServletContext().getRealPath("/uploadFile/");
        //日期格式化
        String format = sdf.format(new Date());
        //创建文件夹--同天上传文件
        File folder = new File(realPath + format);
        //初始化文件路径字符串
        String filePath = "";
        //判断当天文件夹是否已经存在
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        //获取上传文件的原始文件名
        String oldName = uploadFile.getOriginalFilename();
        //生成新的文件名：使用UUID生成随机字符串+文件扩展名
        String newName = UUID.randomUUID() + oldName.substring(oldName.lastIndexOf("."),oldName.length());
        try {
            //将上传的文件保存到服务器指定文件夹中
            //TODO 如果文件内容相同,怎么处理
            uploadFile.transferTo(new File(folder, newName));
            //构建文件的访问路径
            filePath = request.getScheme() + "://" + request.getServerName() + ":" +
                    request.getServerPort() + "/uploadFile/" + format +"/"+ newName;
            FileLog fileLog=new FileLog();
            fileLog.setDate(new Date());
            fileLog.setPath(realPath+format+"\\"+newName);
            fileLog.setFilename(oldName);
            fileService.save(fileLog);
            //TODO
            System.out.println(realPath+format+"\\"+newName);
            System.out.println(filePath);
            // 返回文件的访问路径
            return Result.success(fileLog);

        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("上传失败! ");
        }
    }


    /**
     * 下载文件
     * @param response
     * @param //TODO fileId
     * @return
     */
    @Override

    public Result download(HttpServletResponse response, int fileId){
        try {
            FileLog file = fileService.getById(fileId);

            //从数据库中拿到真实路径
            String path = file.getPath();
            //实例化要下载的文件对象
            File downLoadFile = new File(path);
            //打开一个输入流，从服务器中读取文件
            FileInputStream in = new FileInputStream(downLoadFile);
            //将文件名编码为UTF-8，以便在HTTP头中使用
            String encodedFilename = URLEncoder.encode(file.getFilename(), "UTF-8");
            //！！设置Content-Disposition头，让浏览器将响应识别为文件下载
            response.setHeader("Content-Disposition", "attachment;filename*=UTF-8''" + encodedFilename);
            //获取响应的输出流，让文件内容写入响应
            ServletOutputStream os = response.getOutputStream();
            //使用Apache Commons IO的IOUtils.copy方法将文件内容从输入流复制到输出流
            //TODO 只用java类库实现
            IOUtils.copy(in, os);
            //安静地关闭输入输出流，即使在发生异常的情况下也不会抛出异常
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(os);
        }catch (Exception e){
            return Result.fail("未找到该文件！");
        }
        return Result.success("成功下载文件！");
    }








}
