package cn.qinwh.qbookadmin.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class MenuVo {

    private Integer id;

    //组件路径
    private String component;

    //组件名称
    private String name;

    //路由地址
    private String path;

    //{title: "标题", icon: "图标"}
    private Map<String, String> meta;

    //父菜单编号
    private Integer pid;

    //排序
    private Integer sort;

    //子路由
    private List<MenuVo> children = new ArrayList<>();

    public void addChildren(MenuVo menu){
        children.add(menu);
    }
}
