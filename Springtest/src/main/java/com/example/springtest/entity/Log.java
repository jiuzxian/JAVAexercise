package com.example.springtest.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.sql.Timestamp;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *  mybatis日志
 * </p>
 *
 * @author Lin
 * @since 2023-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("log")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 授权ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 创建者ID
     */
    private Integer userId;
    /**
     * 创建时间
     */
    private Timestamp operateAt;
    /**
     * 操作类型
     */
    private String type;
    /**
     * 操作方法
     */
    private String method;
    /**
     * 操作对象
     */
    private String object;
    /**
     * 是否成功
     */
    private Integer successful;

}
