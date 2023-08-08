//package com.example.springtest.config;
//
//import ch.qos.logback.classic.spi.*;
//import ch.qos.logback.core.db.DBAppenderBase;
//import com.example.springtest.util.TokenUtil;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.Method;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * @Description:
// * @Author: Rocty
// * @Date: 2019-06-18 16:22
// * @Version: 1.0
// */
//public class MyDBAppender extends DBAppenderBase<ILoggingEvent> {
//    @Resource
//    private TokenUtil tokenUtil;
//
//    private String insertSQL;
//    private static final Method GET_GENERATED_KEYS_METHOD;
//
//    private static final int TIME_INDEX = 1;
//    private static final int USER_INDEX = 2;
//    private static final int MESSAGE_INDEX = 3;
//    private static final int LEVEL_STRING_INDEX = 4;
//    private static final int LOGGER_NAME_INDEX = 5;
//    private static final int THREAD_NAME_INDEX = 6;
//    private static final int CALLER_FILENAME_INDEX = 7;
//    private static final int CALLER_CLASS_INDEX = 8;
//    private static final int CALLER_METHOD_INDEX = 9;
//    private static final int CALLER_LINE_INDEX = 10;
//
//    private static final StackTraceElement EMPTY_CALLER_DATA = CallerData.naInstance();
//
//    static {
//        // PreparedStatement.getGeneratedKeys() method was added in JDK 1.4
//        Method getGeneratedKeysMethod;
//        try {
//            // the
//            getGeneratedKeysMethod = PreparedStatement.class.getMethod("getGeneratedKeys", (Class[]) null);
//        } catch (Exception ex) {
//            getGeneratedKeysMethod = null;
//        }
//        GET_GENERATED_KEYS_METHOD = getGeneratedKeysMethod;
//    }
//
//    @Override
//    public void start() {
//        insertSQL = buildInsertSQL();
//        super.start();
//    }
//
//    private static String buildInsertSQL() {
//        return "INSERT INTO log_record " +
//                "(time, user_id, message, logger_name, level_string, thread_name," +
//                "caller_filename, caller_class, caller_method, caller_line)"+
//                "VALUES (?, ?, ?, ? ,?, ?, ?, ?, ?, ?)";
//    }
//
//
//
//
//
//    private void bindLoggingEventWithInsertStatement(PreparedStatement stmt, ILoggingEvent event) throws SQLException {
//        // 从 MDC 中获取 userId
//        String userId = event.getMDCPropertyMap().get("userId");
//        //TODO 每个SET表示什么？
//        stmt.setString(USER_INDEX, userId);
//
//        stmt.setTimestamp(TIME_INDEX, new Timestamp(event.getTimeStamp()));
//        stmt.setString(MESSAGE_INDEX, event.getFormattedMessage());
//        stmt.setString(LEVEL_STRING_INDEX, event.getLevel().toString());
//        stmt.setString(LOGGER_NAME_INDEX, event.getLoggerName());
//        stmt.setString(THREAD_NAME_INDEX, event.getThreadName());
//    }
//
//    private void bindCallerDataWithPreparedStatement(PreparedStatement stmt, StackTraceElement[] callerDataArray) throws SQLException {
//        StackTraceElement caller = extractFirstCaller(callerDataArray);
//        stmt.setString(CALLER_FILENAME_INDEX, caller.getFileName());
//        stmt.setString(CALLER_CLASS_INDEX, caller.getClassName());
//        stmt.setString(CALLER_METHOD_INDEX, caller.getMethodName());
//        stmt.setString(CALLER_LINE_INDEX, Integer.toString(caller.getLineNumber()));
//    }
//
//    @Override
//    protected void subAppend(ILoggingEvent event, Connection connection, PreparedStatement insertStatement) throws Throwable {
//
//        // 只记录特定的日志消息
//        //TODO need值什么时候传递
//        String need = event.getMDCPropertyMap().get("need");
//        if ("1".equals(need)) {
//            bindLoggingEventWithInsertStatement(insertStatement, event);
//            bindCallerDataWithPreparedStatement(insertStatement, event.getCallerData());
//            int updateCount = insertStatement.executeUpdate();
//            if (updateCount != 1) {
//                addWarn("Failed to insert loggingEvent");
//            }
//        }
//    }
//
//    private StackTraceElement extractFirstCaller(StackTraceElement[] callerDataArray) {
//        StackTraceElement caller = EMPTY_CALLER_DATA;
//        if (hasAtLeastOneNonNullElement(callerDataArray))
//            caller = callerDataArray[0];
//        return caller;
//    }
//
//    private boolean hasAtLeastOneNonNullElement(StackTraceElement[] callerDataArray) {
//        return callerDataArray != null && callerDataArray.length > 0 && callerDataArray[0] != null;
//    }
//
//    @Override
//    protected Method getGeneratedKeysMethod() {
//        return GET_GENERATED_KEYS_METHOD;
//    }
//
//    @Override
//    protected String getInsertSQL() {
//        return insertSQL;
//    }
//
//    protected void secondarySubAppend(ILoggingEvent event, Connection connection, long eventId) throws Throwable {
//    }
//}
