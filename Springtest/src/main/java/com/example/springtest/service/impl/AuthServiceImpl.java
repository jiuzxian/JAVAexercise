package com.example.springtest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springtest.entity.Auth;
import com.example.springtest.entity.Log;
import com.example.springtest.entity.Result;
import com.example.springtest.entity.Setting;
import com.example.springtest.mapper.AuthMapper;
import com.example.springtest.service.AuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springtest.service.LogService;
import com.example.springtest.service.SettingService;
import com.example.springtest.vo.AuthVo;
import com.example.springtest.vo.InAuthVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lin
 * @since 2023-07-28
 */
@Service
//@EnableTransactionManagement
public class AuthServiceImpl extends ServiceImpl<AuthMapper, Auth> implements AuthService {

    @Resource
    AuthService authService;

    @Resource
    LogService logService;

    @Resource
    SettingService settingService;

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

    public void test(){
        LambdaQueryWrapper<Auth> authLambdaQueryWrapper = new LambdaQueryWrapper<>();
        authService.remove(authLambdaQueryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    public Result authGive(InAuthVo vo, int userId){
        int id = vo.getUserId();
        List<Integer> list = vo.getList();
        Log log= new Log();
        Result result=new Result();
        //只回滚以下异常，设置回滚点
        Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
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
                if(i == list.size() / 2) throw new RuntimeException("Test exception");
            }

            log.setType("authGive");
            log.setUserId(userId);
            log.setOperateAt( new Timestamp(System.currentTimeMillis()));
            log.setObject(vo.toString());
            log.setSuccessful(1);
            logService.save(log);
            result = Result.success("授权成功！");
        } catch (Exception e) {
            //手工回滚异常，回滚到savePoint
            TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);
            log.setType("authGive");
            log.setUserId(userId);
            log.setObject(vo.toString());
            log.setSuccessful(0);
            logService.save(log);
            //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result = Result.fail();
        }

        // result类返回成功时，一般不重设code编码
        return result;
    }


    /**
     * 根据子项把菜单集合增大完整
     * @param vo1s
     * @return
     */
    //传入最底层菜单的vo集合
    public List<AuthVo> upShow(List<AuthVo> vo1s){
        //实例化一个父集合
        List<AuthVo> parentVos=new ArrayList<>();
        //遍历传来的所有子项
        for(int i=0;i<vo1s.size();i++){
            int sid=vo1s.get(i).getId();
            int pid=settingService.getById(sid).getParent();
            //如果父id为-1，就返回上一次的结果
            if(pid==-1){
                return vo1s;
            }
            else {
                //拿到父集合现有id
                List<Integer> parentIdList;
                parentIdList = parentVos.stream().map(AuthVo::getId).collect(Collectors.toList());
                //当前子项的父id如果已经存在于父集合中
                if(parentIdList.contains(pid)) {
                    //parentVos.stream().filter(parentVo-> parentVo.getId().equals(pid)).findFirst()
                    //找到那个父项，把当前子项添加到child
                    for (int j = 0; j < parentVos.size(); j++) {
                        if (parentVos.get(j).getId().equals(pid)) {
                            parentVos.get(j).getChild().add(vo1s.get(i));
                            break;
                        }
                    }
                }
                else {//若是新父项,把当前子项加入父项后，把新父项加入集合中
                    AuthVo parentVo=new AuthVo();
                    parentVo.setId(pid);
                    parentVo.setName(settingService.getById(pid).getObject());
                    parentVo.getChild().add(vo1s.get(i));
                    parentVo.setUrl("nihao");
                    parentVos.add(parentVo);
                        }
                    }
                }


        //将得到的父项集合作为下一次迭代的子项集
        return upShow(parentVos);
    }
}
