package com.example.springtest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springtest.entity.Auth;
import com.example.springtest.entity.Log;
import com.example.springtest.entity.Result;
import com.example.springtest.mapper.AuthMapper;
import com.example.springtest.service.AuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springtest.service.LogService;
import com.example.springtest.vo.AuthVo;
import com.example.springtest.vo.InAuthVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lin
 * @since 2023-07-28
 */
@Service
public class AuthServiceImpl extends ServiceImpl<AuthMapper, Auth> implements AuthService {

    @Resource
    AuthService authService;

    @Resource
    LogService logService;

    public  List<Auth> findByUId(int id){

        LambdaQueryWrapper<Auth> authLambdaQueryWrapper = new LambdaQueryWrapper<>();
        authLambdaQueryWrapper.eq(Auth::getUserId, id);
        List<Auth> authList = authService.list(authLambdaQueryWrapper);
        return authList;
    }

    public void removeByUId(int id){
        LambdaQueryWrapper<Auth> authLambdaQueryWrapper = new LambdaQueryWrapper<>();
        authLambdaQueryWrapper.eq(Auth::getUserId, id);
        authService.remove(authLambdaQueryWrapper);
    }

    public Result authGive(InAuthVo vo, int userId){
        int id = vo.getUserId();
        List<Integer> list = vo.getList();
        Log log= new Log();
        Result result=new Result();

        try {
            //把现有的都删了
            try {
                authService.removeByUId(id);
            } catch (Exception e) {
            }
            //一个个存
            // 排版 ctrl + alt + l
            // 新增数据时，创建人、创建时间也要进行更新

            for (int i = 0; i < list.size(); i++) {
                int n = list.get(i);
                Auth auth = new Auth();
                auth.setUserId(id);
                auth.setSettingId(n);
                //创建人同步更新
                auth.setCreatedBy(userId);
                auth.setUpdatedBy(userId);
                authService.save(auth);
            }

            log.setType("authGive");
            log.setUserId(userId);
            log.setOperateAt( new Timestamp(System.currentTimeMillis()));
            log.setObject(vo.toString());
            log.setSuccessful(1);
            logService.save(log);
            result = Result.success("授权成功！");
        } catch (Exception e) {
            log.setType("authGive");
            log.setObject(vo.toString());
            log.setSuccessful(0);
            logService.save(log);
            result = Result.fail();
        }

        // result类返回成功时，一般不重设code编码
        return result;
    }


}
