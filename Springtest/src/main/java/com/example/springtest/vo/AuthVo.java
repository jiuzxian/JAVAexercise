package com.example.springtest.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AuthVo {

    private Integer userId;

    private Integer menuId;

    private Integer parentId;

    private String menuName;

}
