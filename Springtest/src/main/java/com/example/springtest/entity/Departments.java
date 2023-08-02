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
 * 
 * </p>
 *
 * @author Lin
 * @since 2023-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("departments")
public class Departments implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 部门名称
     */
    private String departmentName;

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
