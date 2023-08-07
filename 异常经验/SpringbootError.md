# **常见错误和tip**

1. 数据库密码错误：java.sql.SQLException: Access denied for user 'root'@'localhost' (using password: YES)

2. 数据库连接错误：Cause: org.springframework.jdbc.CannotGetJdbcConnectionException: Failed to obtain JDBC Connection; nested exception is com.

3. 空指针异常：service为空--没注入

4. 自动代码实现mybatis-plus：[(38条消息) springboot 之 使用mybatis-plus-generator快速生成数据库对应实体类_enjoy嚣士的博客-CSDN博客](https://blog.csdn.net/u013919153/article/details/110485142?ops_request_misc=&request_id=&biz_id=102&utm_term=springboot自动生成实体mybatisplus&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduweb~default-4-110485142.142^v90^insert_down28v1,239^v2^insert_chatgpt&spm=1018.2226.3001.4187)

5. 数据库mybatis解析问题：org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near '' ----检查主键有没有获取到

6. ！实体类字段要小驼峰！否则@Data生成的get或set无法识别（getName默认写法，n默认小写）

7. **请求参数**和**响应参数**不推荐使用Map,响应参数用**Result**类

8. DruidEncryptorUtils工具类注意，公钥私钥只能生成一次，生成后把静态方法注释掉。要应用时，须在工具类内更改公/私钥

9. 在数据库使用外键会增加数据库开销，一般约束不在数据库加

10. simpl层因为是继承service层，所以要用overide

11. 拦截器应用不能使用new 来创建对象，需要用springboot的自动注入：

    ```java
    @Configuration
    public class WebConfiguration implements WebMvcConfigurer {
        //拦截器应用
    //    @Override
    //    public void addInterceptors(InterceptorRegistry registry) {
    //            // 要使用springboot的自动注入，不能使用new 来创建对象
    //        registry.addInterceptor(new com.example.springtest.config.LoginInterceptor())
    //                .addPathPatterns("/**").excludePathPatterns("/loginPost","/login");
    //        WebMvcConfigurer.super.addInterceptors(registry);
    //    }
    
        @Resource
        private LoginInterceptor loginInterceptor;
    
        //拦截器应用
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(loginInterceptor)
            .addPathPatterns("/**").excludePathPatterns("/loginPost","/login");
        }
    }
    ```

12. 

