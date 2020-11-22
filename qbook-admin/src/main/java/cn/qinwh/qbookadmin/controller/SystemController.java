package cn.qinwh.qbookadmin.controller;

import cn.qinwh.qbookcommon.utils.ReturnMsg;
import cn.qinwh.qbooksystem.annotation.NoLogin;
import cn.qinwh.qbooksystem.entity.SysMenu;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system")
@NoLogin
public class SystemController {

    @GetMapping("/hello")
    public ReturnMsg hello(){
        return ReturnMsg.success("hello", null);
    }

}
