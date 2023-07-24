package com.example.springtest.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.time.LocalTime;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2023-07-20
 */
@Data
//TODO 添加Accessors和EqualsAndHashCode的目的是什么
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("attendance")
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;
    //TODO 参数名统一使用小驼峰
    @TableField("EmployeeID")
    private Integer EmployeeID;

    @TableField("Date")
    private LocalDate Date;

    @TableField("StartTime")
    private LocalTime StartTime;

    @TableField("EndTime")
    private LocalTime EndTime;


}
