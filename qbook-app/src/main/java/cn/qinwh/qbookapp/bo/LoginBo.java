package cn.qinwh.qbookapp.bo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @program: qbook
 * @description: 登录参数
 * @author: qinwh
 * @create: 2021-01-21 10:43
 **/
@Data
public class LoginBo {

    /**
     * 账号
     */
    @NotNull(message = "账号密码不能为空")
    @NotEmpty(message = "账号密码不能为空")
    private String username;

    /**
     * 密码
     */
    @NotNull(message = "账号密码不能为空")
    @NotEmpty(message = "账号密码不能为空")
    private String password;
}
