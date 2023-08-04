//package com.example.springtest;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.*;
//
//import com.example.springtest.entity.Auth;
//import com.example.springtest.entity.Result;
//import com.example.springtest.service.AuthService;
//import com.example.springtest.vo.InAuthVo;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.Arrays;
//
//public class AuthServiceTest {
//
//    private AuthService authService;
//    private LogService logService;
//    private AuthGiveService authGiveService;
//
//    @Before
//    public void setUp() {
//        // 创建Mock对象
//        authService = mock(AuthService.class);
//        logService = mock(LogService.class);
//
//        // 创建待测试对象，并将Mock对象注入
//        authGiveService = new AuthGiveService(authService, logService);
//    }
//
//    @Test
//    public void testAuthGiveSuccess() {
//        // 构造测试数据
//        InAuthVo vo = new InAuthVo();
//        vo.setUserId(1);
//        vo.setList(Arrays.asList(1, 2, 3));
//        int userId = 100;
//
//        // 在save方法调用时，模拟返回结果
//        doAnswer(invocation -> {
//            Auth auth = (Auth) invocation.getArguments()[0];
//            auth.setId(1);
//            return null;
//        }).when(authService).save(any(Auth.class));
//
//        // 调用待测试方法
//        Result result = authGiveService.authGive(vo, userId);
//
//        // 验证结果是否符合预期
//        assertEquals(Result.SUCCESS, result.getCode());
//        assertEquals("授权成功！", result.getMessage());
//
//        // 验证authService的方法是否被调用了一次
//        verify(authService, times(1)).removeByUId(1);
//        verify(authService, times(3)).save(any(Auth.class));
//
//        // 验证logService的方法是否被调用了一次
//        verify(logService, times(1)).save(any(Log.class));
//    }
//
//    @Test
//    public void testAuthGiveFailed() {
//        // 构造测试数据，这里我们假设保存Auth数据失败
//        InAuthVo vo = new InAuthVo();
//        vo.setUserId(1);
//        vo.setList(Arrays.asList(1, 2, 3));
//        int userId = 100;
//
//        // 在save方法调用时，模拟抛出异常
//        doThrow(new RuntimeException("保存Auth数据失败")).when(authService).save(any(Auth.class));
//
//        // 调用待测试方法
//        Result result = authGiveService.authGive(vo, userId);
//
//        // 验证结果是否符合预期
//        assertEquals(Result.FAIL, result.getCode());
//
//        // 验证authService的方法是否被调用了一次
//        verify(authService, times(1)).removeByUId(1);
//
//        // 验证logService的方法是否被调用了一次
//        verify(logService, times(1)).save(any(Log.class));
//    }
//}
