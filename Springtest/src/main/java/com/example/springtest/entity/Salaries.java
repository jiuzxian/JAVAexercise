package com.example.springtest.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
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
@TableName("salaries")
public class Salaries implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("EmployeeID")
    private Integer EmployeeID;

    @TableField("Salary")
    private BigDecimal Salary;

    @TableField("EffectiveDate")
    private LocalDate EffectiveDate;


}
