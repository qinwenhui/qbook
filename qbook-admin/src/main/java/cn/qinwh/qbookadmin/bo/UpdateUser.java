package cn.qinwh.qbookadmin.bo;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * @program: qbook
 * @description: 编辑用户时入参
 * @author: qinwh
 * @create: 2021-01-11 10:19
 **/
@Data
public class UpdateUser {

    @NotNull(message = "id不能为空")
    @Digits(integer = 1, fraction = 1, message = "id必须为整数")
    private String id;

    @Digits(integer = 1, fraction = 1, message = "状态必须为整数")
    private String status;
}
