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
 * 权限
 * </p>
 *
 * @author Lin
 * @since 2023-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("auth")
public class Auth implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 授权ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 最小菜单ID
     */
    private Integer settingId;

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
