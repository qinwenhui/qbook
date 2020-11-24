package cn.qinwh.qbookadmin.controller;

import cn.qinwh.qbookadmin.bo.UserBo;
import cn.qinwh.qbookadmin.service.UserService;
import cn.qinwh.qbookadmin.vo.MenuVo;
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
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: qbook
 * @description: 系统管理员
 * @author: qinwh
 * @create: 2020-11-19 12:49
 **/
@RestController
@RequestMapping("/admin/admin")
@Slf4j
public class AdminController {

    @Autowired
    @Qualifier("sysMenuServiceImpl")
    private SysMenuService sysMenuService;
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    /**
     * 获取菜单列表（没有进行路由格式化之前的原始数据）
     * @return
     */
    @GetMapping("/userMenuList")
    public ReturnMsg userMenuList(){
        SysUser user = LoginUserUtils.getLoginUser();
        List<SysMenu> menuList = sysMenuService.getUserMenu(user.getId());
        return ReturnMsg.success("获取菜单成功", menuList);
    }

    /**
     * 获取菜单列表（针对Vue的路由表设计）
     * 原始 {url(url需要变成{path, component, name}), meta:{name, icon}}
     * Vue {path, component, name, meta:{title, icon}}
     * @return
     */
    @GetMapping("/usermenu")
    public ReturnMsg userMenu(){
        SysUser user = LoginUserUtils.getLoginUser();
        List<SysMenu> menuList = sysMenuService.getUserMenu(user.getId());
        List<MenuVo> menuVoList = new ArrayList<>();
        for(SysMenu menu: menuList){
            menuVoList.add(menuToVo(menu));
        }
        int i = 0;
        while(i<menuVoList.size()){
            boolean isRemove = generateMenu(menuVoList, menuVoList.get(i));
            //如果有移除，i 不用自增
            if(!isRemove){
                i ++;
            }
        }
        return ReturnMsg.success("获取菜单成功", menuVoList);
    }

    /**
     * 获取全部菜单列表
     * @return
     */
    @GetMapping("/allMenuList")
    public ReturnMsg allMenuList(){
        List<SysMenu> menuList = sysMenuService.queryAll();
        return ReturnMsg.success("获取全部菜单成功", menuList);
    }

    /**
     * 获取用户列表
     * @param bo
     * @return
     */
    @GetMapping("/userlist")
    public ReturnMsg userList(@Valid UserBo bo){
        PageInfo<User> userPageInfo = userService.selectByBo(bo);
        return ReturnMsg.success("user", userPageInfo);
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
            return ReturnMsg.fail("账号密码不能为空", null);
        }
        User whereUser = new User();
        whereUser.setUsername(username);
        whereUser.setPassword(password);
        User user = userService.queryOne(whereUser);
        if(user == null){
            return ReturnMsg.fail("账号或密码错误", null);
        }
        //生成redis
        String token = CharacterUtils.getRandomString(32);
        RedisUtils.set(token, user, RedisConst.TOKEN_SAVE_TIME);
        return ReturnMsg.success("登录成功", token);
    }

    /**
    * @Description: 退出登录
    * @Param: []
    * @return: cn.qinwh.qbookcommon.utils.ReturnMsg
    * @Author: qinwh
    * @Date: 2020/11/24
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
    * @Date: 2020/11/24
    */
    @GetMapping("/loginUserInfo")
    public ReturnMsg loginUserInfo(){
        SysUser sysUser = LoginUserUtils.getLoginUser();
        User user = userService.queryByPrimaryKey(sysUser.getId());
        if(user != null){
            return ReturnMsg.success("获取用户信息成功", user);
        }
        return ReturnMsg.custom(ReturnMsg.LOGIN_FALSE, "获取登录用户信息失败", null);
    }

    private boolean generateMenu(List<MenuVo> srcList, MenuVo menu){
        //返回值：true表示有移除，false表示没有移除，不然会导致遍历漏掉
        if(menu.getPid() == 0){
            //该菜单属于顶级菜单
            return false;
        }
        for(MenuVo vo : srcList){
            if(menu.getPid() == vo.getId()){
                //说明这个menu菜单是vo菜单的子菜单，放进去
                vo.addChildren(menu);
                //从原来的位置移除掉
                srcList.remove(menu);
                return true;
            }else{
                //深度扫描(递归检测菜单的子菜单的子菜单的子菜单。。。。)
                generateMenu(vo.getChildren(), menu);
            }
        }
        return false;
    }

    private MenuVo menuToVo(SysMenu menu){
        MenuVo vo = new MenuVo();
        vo.setId(menu.getId());
        //这个url包含有保存path、component、name的json字符串信息，先直接转为vo
        String jsonStr = menu.getUrl();
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            String path = jsonObject.getString("path");
            String name = jsonObject.getString("name");
            String component = jsonObject.getString("component");
            vo.setPath(path);
            vo.setName(name);
            vo.setComponent(component);
        } catch (JSONException e) {
            log.error("菜单url json实例化出错:{}", e.getMessage());
        }
        Map<String, String> meta = new HashMap<>();
        meta.put("title", menu.getName());
        meta.put("icon", menu.getIcon());
        vo.setMeta(meta);
        vo.setPid(menu.getPid());
        vo.setSort(menu.getSort());
        return vo;
    }

}
