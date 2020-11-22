package cn.qinwh.qbookadmin.bo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RoleBo {

    /**
     * 角色名称
     */
    @NotNull(message = "角色名称不能为空")
    private String name;
    /**
     * 角色描述
     */
    private String description;

    /**
    * 状态
     */
    private String status;

}
