package cn.qinwh.qbooksystem.service.impl;

import cn.qinwh.qbooksystem.entity.SysMenu;
import cn.qinwh.qbooksystem.entity.SysRoleMenu;
import cn.qinwh.qbooksystem.entity.SysUserRole;
import cn.qinwh.qbooksystem.mapper.SysRoleMenuMapper;
import cn.qinwh.qbooksystem.mapper.SysUserRoleMapper;
import cn.qinwh.qbooksystem.service.SysMenuService;
import cn.qinwh.mybatis.qservice.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu> implements SysMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 查询用户菜单集合
     *
     * @return 用户所有菜单信息
     */
    @Override
    public List<SysMenu> getUserMenu(Integer userId) {
        List<SysMenu> menuList = new ArrayList<>();
        //获取该用户所有角色
        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(userId);
        List<SysUserRole> userRoleList = sysUserRoleMapper.select(userRole);
        for(SysUserRole tmpUserRole : userRoleList){
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setRoleId(tmpUserRole.getRoleId());
            List<SysRoleMenu> roleMenuList = sysRoleMenuMapper.select(roleMenu);
            for(SysRoleMenu tmpRoleMenu: roleMenuList){
                SysMenu menu = mapper.selectByPrimaryKey(tmpRoleMenu.getMenuId());
                menuList.add(menu);
            }
        }
        return menuList;
    }
}