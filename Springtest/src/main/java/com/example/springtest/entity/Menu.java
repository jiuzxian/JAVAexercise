package com.example.springtest.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Lin
 * @since 2023-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("menu")
public class Menu implements Serializable {
    //TODO 不用代码生成器，知道数据库类型对应的java类型吗？
    private static final long serialVersionUID = 1L;

    private Integer id;
    //TODO 字段注释
    private String object;

    private Integer isOn;
    //TODO 为什么用LocalDateTime？
    private LocalDateTime createdAt;

    private Integer createdBy;

    private LocalDateTime updatedAt;

    private Integer updatedBy;

    private String remark;


}
