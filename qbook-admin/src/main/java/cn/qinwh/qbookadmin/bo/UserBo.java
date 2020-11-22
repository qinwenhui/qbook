package cn.qinwh.qbookadmin.bo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserBo {
    /**
     * 当前页号
     */
    @NotNull(message="页码不能为空")
    private String pageNo;
    /**
     * 页大小
     */
    @NotNull(message="页大小不能为空")
    private String pageSize;
    /**
     * 用户编号
     */
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 类别:0=临时,1=注册
     */
    private String type;
    /**
     * vip状态:1=是,0=否
     */
    private String vip;
}
