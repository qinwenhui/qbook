package cn.qinwh.qbookapp.controller;

import cn.qinwh.qbookapp.service.UserService;
import cn.qinwh.qbookcommon.utils.CharacterUtils;
import cn.qinwh.qbookcommon.utils.ReturnMsg;
import cn.qinwh.qbooksystem.annotation.NoLogin;
import cn.qinwh.qbooksystem.constant.RedisConst;
import cn.qinwh.qbooksystem.entity.SysUser;
import cn.qinwh.qbooksystem.utils.LoginUserUtils;
import cn.qinwh.qbooksystem.utils.RedisUtils;
import cn.qinwh.qbookapp.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: qbook
 * @description: 用户控制器
 * @author: qinwh
 * @create: 2021-01-11 12:55
 **/
@Controller
@RequestMapping("/app/user")
@ResponseBody
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    /**
    * @Description: 登录
    * @Param: [username, password]
    * @return: cn.qinwh.qbookcommon.utils.ReturnMsg
    * @Author: qinwh
    * @Date: 2021/1/11
    */
    @PostMapping("/login")
    @NoLogin
    public ReturnMsg login(HttpServletRequest request, String username, String password){
        if(username == null || password == null){
            return ReturnMsg.fail("账号或密码不能为空", null);
        }
        return userService.login(request, username, password);
    }

    /**
    * @Description: 注册
    * @Param: [username, password]
    * @return: cn.qinwh.qbookcommon.utils.ReturnMsg
    * @Author: qinwh
    * @Date: 2021/1/11
    */
    @PostMapping("/register")
    @NoLogin
    public ReturnMsg register(HttpServletRequest request, String username, String password){
        if(username == null || password == null){
            return ReturnMsg.fail("账号或密码不能为空", null);
        }
        return userService.register(request, username, password);
    }

    /**
     * @Description: 退出登录
     * @Param: []
     * @return: cn.qinwh.qbookcommon.utils.ReturnMsg
     * @Author: qinwh
     * @Date: 2021/1/11
     */
    @GetMapping("/logout")
    public ReturnMsg logout(){
        String userToken = LoginUserUtils.getToken();
        RedisUtils.delete(userToken);
        return ReturnMsg.custom(ReturnMsg.LOGIN_FALSE, "注销成功", userToken);
    }

    /**
     * @Description: 获取登录用户信息
     * @Param: []
     * @return: cn.qinwh.qbookcommon.utils.ReturnMsg
     * @Author: qinwh
     * @Date: 2021/1/11
     */
    @GetMapping("/loginUserInfo")
    public ReturnMsg loginUserInfo(){
        SysUser sysUser = LoginUserUtils.getLoginUser();
        User user = userService.queryByPrimaryKey(sysUser.getId());
        if(user != null){
            //去掉敏感信息
            user.setPassword("");
            user.setPaypass("");
            user.setLoginNums(null);
            user.setLoginIp(null);
            user.setRegisterIp(null);
            user.setUseragent(null);
            return ReturnMsg.success("获取用户信息成功", user);
        }
        return ReturnMsg.custom(ReturnMsg.LOGIN_FALSE, "获取登录用户信息失败", null);
    }
}
