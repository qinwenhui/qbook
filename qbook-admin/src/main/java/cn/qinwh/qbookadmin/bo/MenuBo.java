package cn.qinwh.qbookadmin.bo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MenuBo {

    /**
     * 菜单名称
     */
    @NotNull(message = "菜单名称不能为空")
    private String name;
    /**
     * 菜单地址
     */
    @NotNull(message = "菜单地址不能为空")
    private String url;
    /**
     * 上级编号,0 为顶级菜单
     */
    @NotNull(message = "上级菜单编号不能为空")
    private String pid;
    /**
     * 图标
     */
    private String icon;
    /**
     * 排序
     */
    @NotNull(message = "排序不能为空")
    private String sort;

}
