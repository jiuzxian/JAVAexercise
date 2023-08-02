package com.example.springtest.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.sql.Timestamp;
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
@TableName("menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 对象名
     */
    private String object;

    /**
     * 是否启用
     */
    private Integer isOn;

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
