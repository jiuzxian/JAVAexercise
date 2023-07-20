package com.example.springtest.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("employees")
public class Employees implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "EmployeeID", type = IdType.AUTO)
    private Integer EmployeeID;

    @TableField("Name")
    private String Name;

    @TableField("DepartmentID")
    private Integer DepartmentID;

    @TableField("Position")
    private String Position;

    @TableField("HireDate")
    private LocalDate HireDate;


}
