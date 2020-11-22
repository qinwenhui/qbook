package cn.qinwh.qbookadmin.bo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PermissionBo {

    /**
     * 权限名称
     */
    @NotNull(message = "权限名称不能为空")
    private String name;
    /**
     * 权限地址
     */
    @NotNull(message = "权限地址不能为空")
    private String url;
    /**
     * 权限描述
     */
    private String description;
    /**
     * 状态 0 正常
     */
    private String status;

}
