package cn.qinwh.qbookadmin.controller;

import cn.qinwh.qbookadmin.bo.UserBo;
import cn.qinwh.qbookadmin.service.UserService;
import cn.qinwh.qbookcommon.utils.CharacterUtils;
import cn.qinwh.qbookcommon.utils.ReturnMsg;
import cn.qinwh.qbooksystem.annotation.NoLogin;
import cn.qinwh.qbooksystem.constant.RedisConst;
import cn.qinwh.qbooksystem.entity.SysMenu;
import cn.qinwh.qbookadmin.entity.User;
import cn.qinwh.qbooksystem.entity.SysUser;
import cn.qinwh.qbooksystem.service.SysMenuService;
import cn.qinwh.qbooksystem.utils.LoginUserUtils;
import cn.qinwh.qbooksystem.utils.RedisUtils;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ReturnMsg userList(@Valid UserBo bo){
        SysUser user = LoginUserUtils.getLoginUser();

        PageInfo<User> userPageInfo = userService.selectByBo(bo);
        return ReturnMsg.success("user", user);
    }

    /**
     * 登录---（目前暂时只做这简单版的）
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    @NoLogin
    public ReturnMsg login(String username, String password){
        if(username == null || password == null){
            return ReturnMsg.success("账号密码不能为空", null);
        }
        User whereUser = new User();
        whereUser.setUsername(username);
        whereUser.setPassword(password);
        User user = userService.queryOne(whereUser);
        if(user == null){
            return ReturnMsg.success("账号或密码错误", null);
        }
        //生成redis
        String token = CharacterUtils.getRandomString(32);
        RedisUtils.set(token, user, RedisConst.TOKEN_SAVE_TIME);
        return ReturnMsg.success("登录成功", token);
    }
}
