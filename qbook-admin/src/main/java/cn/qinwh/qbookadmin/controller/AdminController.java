package cn.qinwh.qbookadmin.controller;

import cn.qinwh.qbookcommon.utils.ReturnMsg;
import cn.qinwh.qbooksystem.annotation.NoLogin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: qbook
 * @description: 系统管理员
 * @author: qinwh
 * @create: 2020-11-19 12:49
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/hello")
    @NoLogin
    public ReturnMsg hello(){

        return ReturnMsg.success("hello", null);
    }
}
