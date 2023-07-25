package com.example.springtest.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
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
 * @since 2023-07-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("salaries")
public class Salaries implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer employeeId;

    private LocalDate effectiveDate;

    private Integer id;

    private Long salary;

    private Integer isDelete;


}
