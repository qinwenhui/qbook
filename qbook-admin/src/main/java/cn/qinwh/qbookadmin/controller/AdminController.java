package cn.qinwh.qbookadmin.controller;

import cn.qinwh.qbookadmin.bo.UserQuery;
import cn.qinwh.qbookadmin.service.UserService;
import cn.qinwh.qbookcommon.utils.ReturnMsg;
import cn.qinwh.qbooksystem.annotation.NoLogin;
import cn.qinwh.qbooksystem.entity.SysMenu;
import cn.qinwh.qbookadmin.entity.User;
import cn.qinwh.qbooksystem.service.SysMenuService;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @program: qbook
 * @description: 系统管理员
 * @author: qinwh
 * @create: 2020-11-19 12:49
 **/
@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    @Autowired
    @Qualifier("sysMenuServiceImpl")
    private SysMenuService sysMenuService;
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @GetMapping("/hello")
    @NoLogin
    public ReturnMsg hello(){
        List<SysMenu> menuList = sysMenuService.getUserMenu(1);
        return ReturnMsg.success("hello", menuList);
    }

    @GetMapping("/userlist")
    @NoLogin
    public ReturnMsg userList(@Valid UserQuery bo){
        PageInfo<User> userPageInfo = userService.selectByBo(bo);
        return ReturnMsg.success("user", userPageInfo);
    }
}
