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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("attendance")
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("EmployeeID")
    private Integer EmployeeID;

    @TableField("Date")
    private LocalDate Date;

    @TableField("StartTime")
    private LocalTime StartTime;

    @TableField("EndTime")
    private LocalTime EndTime;


}
