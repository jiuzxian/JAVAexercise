package com.example.springtest.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("departments")
public class Departments implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "DepartmentID", type = IdType.AUTO)
    private Integer DepartmentID;

    @TableField("DepartmentName")
    private String DepartmentName;


}
