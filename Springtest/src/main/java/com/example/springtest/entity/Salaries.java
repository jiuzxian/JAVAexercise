package com.example.springtest.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import java.sql.Timestamp;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2023-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("salaries")
public class Salaries implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工ID
     */
    private Integer employeeId;

    /**
     * 生效日期
     */
    private LocalDate effectiveDate;

    /**
     * 薪水记录ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 薪水
     */
    private Long salary;

    /**
     * 是否已删除
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private Timestamp createdAt;

    /**
     * 创建者
     */
    private Integer createdBy;

    /**
     * 更新时间
     */
    private Timestamp updatedAt;

    /**
     * 更新者
     */
    private Integer updatedBy;

    /**
     * 备注
     */
    private String remark;


}
