package cn.qinwh.qbookapp.service.impl;

import cn.qinwh.mybatis.qservice.common.BaseServiceImpl;
import cn.qinwh.qbookapp.entity.User;
import cn.qinwh.qbookapp.service.UserService;
import cn.qinwh.qbookcommon.utils.CharacterUtils;
import cn.qinwh.qbookcommon.utils.ClientUtils;
import cn.qinwh.qbookcommon.utils.ReturnMsg;
import cn.qinwh.qbookcommon.utils.StringUtils;
import cn.qinwh.qbooksystem.constant.RedisConst;
import cn.qinwh.qbooksystem.entity.SysPermission;
import cn.qinwh.qbooksystem.entity.SysUserRole;
import cn.qinwh.qbooksystem.service.SysPermissionService;
import cn.qinwh.qbooksystem.service.SysUserRoleService;
import cn.qinwh.qbooksystem.utils.RedisUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private final SysUserRoleService sysUserRoleService;

    private final SysPermissionService sysPermissionService;

    @Override
    public ReturnMsg login(HttpServletRequest request, String username, String password) {
        User whereUser = new User();
        whereUser.setUsername(username);
        whereUser.setPassword(password);
        User user = this.queryOne(whereUser);
        if(user == null){
            return ReturnMsg.fail("账号或密码错误", null);
        }
        if(user.getStatus() != 1){
            return ReturnMsg.fail("该账号已被冻结，请联系管理员", null);
        }
        //增加登录次数
        user.setLoginNums(user.getLoginNums()+1);
        //判断登录次数，如果大于1，则设置是否是第一次登录的字段
        if(user.getLoginNums() > 1){
            user.setIsFirst(0);
        }
        //设置登录时间和IP
        user.setLastTime(new Date());
        user.setLoginIp(ClientUtils.getIp(request));
        //设置客户端信息
        user.setUseragent(ClientUtils.getPlatform(request));
        //保存登录之后更新的用户信息
        this.updateSelective(user);
        //生成redis
        String token = CharacterUtils.getRandomString(32);
        RedisUtils.set(token, user, RedisConst.TOKEN_SAVE_TIME);
        //设置用户的权限到缓存
        List<SysPermission> permissionList = sysPermissionService.getUserPermission(user.getId());
        RedisUtils.set(RedisConst.USER_PERMISSION + user.getId(), permissionList, RedisConst.TOKEN_SAVE_TIME);
        return ReturnMsg.success("登录成功", token);
    }

    @Override
    public ReturnMsg register(HttpServletRequest request, String username, String password) {
        //暂不验证密码复杂度,只规定长度
        if(username.length() < 6 || password.length() < 6){
            return ReturnMsg.fail("账号密码长度过短，请输入6位字符以上的账号和密码", null);
        }
        User newUser = new User();
        newUser.setUsername(username);
        //检查是否存在该账号
        List<User> userList = this.queryListByWhere(newUser);
        if(userList != null && !userList.isEmpty()){
            return ReturnMsg.fail("该账号已存在", null);
        }
        newUser.setPassword(password);
        //设置注册IP,注册时间
        newUser.setRegisterIp(ClientUtils.getIp(request));
        newUser.setRegisterTime(new Date());
        //设置是否是第一次登录
        newUser.setIsFirst(1);
        //设置登录次数
        newUser.setLoginNums(0);
        //设置注册类别,0=临时,1=注册
        newUser.setType(1);
        //设置客户端信息
        newUser.setUseragent(ClientUtils.getPlatform(request));
        //保存
        if(this.saveSelect(newUser) != 1){
            log.error("注册失败，系统错误{}", UserServiceImpl.class.getName());
            return ReturnMsg.fail("注册失败，系统错误", null);
        }
        //设置普通用户角色
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(newUser.getId());
        //目前读者角色编号为6
        sysUserRole.setRoleId(6);
        sysUserRole.setStatus(0);
        sysUserRoleService.saveSelect(sysUserRole);
        return ReturnMsg.success("注册成功", "您的用户名：" + username + " 密码：" + password);
    }
}