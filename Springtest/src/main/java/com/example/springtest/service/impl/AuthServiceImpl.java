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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限服务实现类
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

    @Resource
    AuthMapper authMapper;

    // 实现的的方法要加Override注解
    @Override
    public List<Auth> findByUId(int id) {

        LambdaQueryWrapper<Auth> authLambdaQueryWrapper = new LambdaQueryWrapper<>();
        authLambdaQueryWrapper.eq(Auth::getUserId, id);
        List<Auth> authList = authService.list(authLambdaQueryWrapper);
        return authList;
    }

    @Override
    public void removeByUId(int id) {
        LambdaQueryWrapper<Auth> authLambdaQueryWrapper = new LambdaQueryWrapper<>();
        authLambdaQueryWrapper.eq(Auth::getUserId, id);
        authService.remove(authLambdaQueryWrapper);
    }


    // 事务的隔离性、传播性
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Result authGive(InAuthVo vo, int userId) {
        int id = vo.getUserId();
        List<Integer> list = vo.getList();

        List<Setting> settings = settingService.listByIds(list);
        //查找需要给的权限的父是否是-1，若是则fail---》 join
//        for (int i = 0; i < list.size(); i++) {
//
//        }

        // 对象创建在需要的地方
        Log log = new Log();
        Result result = new Result();
        //只回滚以下异常，设置回滚点
        Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
        try {
            //把现有的都删了
            try {
                authService.removeByUId(id);
            } catch (Exception e) {
                System.out.println("没有权限重叠！");
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
                // 括号
//                if (i == list.size() / 2) {
//                    throw new RuntimeException("Test exception");
//                }
            }
            // 以下代码基本一致，考虑封装
            logService.logStatus("authGive", userId, vo.toString(), 1);
            return Result.success();

        } catch (Exception e) {
            //手工回滚异常，回滚到savePoint
            TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);
            logService.logStatus("authGive", userId, vo.toString(), 0);
            return Result.fail();

        }

        // result类返回成功时，一般不重设code编码
        //return result;
    }


    /**
     * 根据子项把菜单集合增大完整
     *
     * @param vo1s
     * @return
     */
    //TODO 参数命名尽量具备自解释的作用
    @Override
    //传入最底层菜单的vo集合
    public List<AuthVo> upShow(List<AuthVo> vo1s) {
        //实例化一个父集合
        List<AuthVo> parentVos = new ArrayList<>();
        //遍历传来的所有子项
        //TODO 出现超过三层的分支就要考虑一下你的代码是不是存在优化的可能性
        for (int i = 0; i < vo1s.size(); i++) {
            int sid = vo1s.get(i).getId();
            //TODO 在递归的方法中尽量减少数据库操作
            int pid = settingService.getById(sid).getParent();
            //如果父id为-1，就返回上一次的结果
            //TODO 调整方向:改条件判断;在添加数据时做好判断;调整处理逻辑
            if (pid == -1) {
                return vo1s;
            } else {
                //拿到父集合现有id
                List<Integer> parentIdList;
                parentIdList = parentVos.stream().map(AuthVo::getId).collect(Collectors.toList());
                //当前子项的父id如果已经存在于父集合中
                if (parentIdList.contains(pid)) {
                    int finalI = i;
                    //找到那个父项，把当前子项添加到child
                    parentVos.stream().filter(parentVo-> parentVo.getId().equals(pid)).findFirst().ifPresent(parentVo -> parentVo.getChild().add(vo1s.get(finalI)));
                } else {//若是新父项,把当前子项加入父项后，把新父项加入集合中
                    AuthVo parentVo = new AuthVo();
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

//
    //TODO 方法注释
    @Override
    public List<AuthVo> getMenuHierarchy(int userId) {
        return authMapper.getMenuHierarchy(userId);
    }
//
//
//    @Override
//    public List<AuthVo> upShow(int userId) {
//        List<AuthVo> menuHierarchy = getMenuHierarchy(userId); // 使用你新定义的方法获取菜单层级
//        return menuHierarchy;
//    }


//    @Override
//    public List<AuthVo> upShow(List<AuthVo> vo1s) {
//        List<AuthVo> parentVos = new ArrayList<>();
//        for (AuthVo child : vo1s) {
//            int sid = child.getId();
//            AuthVo parent = settingService.getPbyS(sid); ////
//            if (settingService.getById(sid).getParent()==-1) {
//                return vo1s;
//            } else {
//                List<Integer> parentIdList = parentVos.stream().map(AuthVo::getId).collect(Collectors.toList());
//                if (parentIdList.contains(parent.getId())) {
//                    parentVos.stream()
//                            .filter(parentVo -> parentVo.getId().equals(parent.getId()))
//                            .findFirst()
//                            .ifPresent(parentVo -> parentVo.getChild().add(child));
//                } else {
//                    parent.getChild().add(child);
//                    parentVos.add(parent);
//                }
//            }
//        }
//        return upShow(parentVos);
//    }

}