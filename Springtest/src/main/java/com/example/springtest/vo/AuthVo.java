package com.example.springtest.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AuthVo {

    // 父菜单
    private String name;
    private Integer id;
    private String url;
    // 子菜单
    //TODO 有需要的时候再实例化对象
    //TODO 排版
    private List<AuthVo>child=new ArrayList<>();

}
