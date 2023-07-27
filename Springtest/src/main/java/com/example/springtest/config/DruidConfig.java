package com.example.springtest.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.example.springtest.util.DruidEncryptorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Description:
 * @Author Fanth
 * @Date 2021/7/23
 * @Version 1.0
 **/
@Configuration
public class DruidConfig {
    private static final Logger logger = LoggerFactory.getLogger(DruidConfig.class);

    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
//    @Value("${spring.datasource.druid.initial-size}")
//    private int initialSize;
//    @Value("${spring.datasource.druid.max-active}")
//    private int maxActive;
//    @Value("${spring.datasource.druid.min-idle}")
//    private int minIdle;
//    @Value("${spring.datasource.druid.max-wait}")
//    private int maxWait;
//    @Value("${spring.datasource.druid.keepAlive}")
//    private boolean keepAlive;
//    @Value("${spring.datasource.druid.keepalive-between-time-millis}")
//    private int keepAliveBetweenTimeMillis;
//    @Value("${spring.datasource.druid.pool-prepared-statements}")
//    private boolean poolPreparedStatements;
//    @Value("${spring.datasource.druid.max-pool-prepared-statement-per-connection-size}")
//    private int maxPoolPreparedStatementPerConnectionSize;
//    @Value("${spring.datasource.druid.time-between-eviction-runs-millis}")
//    private int timeBetweenEvictionRunsMillis;
//    @Value("${spring.datasource.druid.min-evictable-idle-time-millis}")
//    private int minEvictableIdleTimeMillis;
//    @Value("${spring.datasource.druid.max-evictable-idle-time-millis}")
//    private int maxEvictableIdleTimeMillis;
//    @Value("${spring.datasource.druid.validation-query}")
//    private String validationQuery;
//    @Value("${spring.datasource.druid.test-while-idle}")
//    private boolean testWhileIdle;
//    @Value("${spring.datasource.druid.test-on-borrow}")
//    private boolean testOnBorrow;
//    @Value("${spring.datasource.druid.test-on-return}")
//    private boolean testOnReturn;
//    @Value("${spring.datasource.druid.filters}")
//    private String filters;
//    @Value("{spring.datasource.druid.connection-properties}")
//    private String connectionProperties;
    //@Value("{spring.datasource.druid.connect-properties.config.decrypt}")
    //private String decrypt;
    //@Value("{spring.datasource.druid.connect-properties.config.decrypt.key}")
    //private String decryptValue;
//
//    @Value("${spring.datasource.druid.connection-properties}")
//    private String connectProperties;

    @Bean
    public DataSource druidDataSource(){
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(dbUrl);
        datasource.setUsername(username);
        password = DruidEncryptorUtils.decode(password);
        System.out.println(password);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
//        datasource.setInitialSize(initialSize);
//        datasource.setMinIdle(minIdle);
//        datasource.setMaxActive(maxActive);
//        datasource.setMaxWait(maxWait);
//        datasource.setKeepAlive(keepAlive);
//        datasource.setKeepAliveBetweenTimeMillis(keepAliveBetweenTimeMillis);
//        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
//        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
//        datasource.setMaxEvictableIdleTimeMillis(maxEvictableIdleTimeMillis);
//        datasource.setValidationQuery(validationQuery);
//        datasource.setTestWhileIdle(testWhileIdle);
//        datasource.setTestOnBorrow(testOnBorrow);
//        datasource.setTestOnReturn(testOnReturn);
//        datasource.setPoolPreparedStatements(poolPreparedStatements);
//        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
//        try {
//            datasource.setEnable(true);
//            datasource.setFilters(filters);
//        } catch (Exception e) {
//            logger.error("druid configuration initialization filter", e);
//        }

        Properties connectProperties = new Properties();
        connectProperties.setProperty("config.decrypt","true");
        connectProperties.setProperty("config.decrypt.key","MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJu18+2HLbMCxBS5DqBws98DB2QG8V9gz4zwHRpq8PRwUWNR5280VFz8/D7HF1s5D/SuQ3qBx+eqZA5BK1xOEM0CAwEAAQ==");
        //datasource.setConnectionProperties(connectProperties);
//
        datasource.setConnectProperties(connectProperties);
        return datasource;
    }

//    @Bean
//    public ServletRegistrationBean statViewServlet(){
//        ServletRegistrationBean<StatViewServlet> statView = new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");
//
//        Map<String,String> initParameter = new HashMap<>();
//        initParameter.put("loginUsername","admin");
//        initParameter.put("loginPassword","123456");
//
//        statView.setInitParameters(initParameter);
//        return statView;
//    }

}
