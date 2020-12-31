package cn.qinwh.qbooksystem.service.impl;

import cn.qinwh.qbooksystem.entity.SysMenu;
import cn.qinwh.qbooksystem.entity.SysRoleMenu;
import cn.qinwh.qbooksystem.entity.SysUserRole;
import cn.qinwh.qbooksystem.mapper.SysMenuMapper;
import cn.qinwh.qbooksystem.mapper.SysRoleMenuMapper;
import cn.qinwh.qbooksystem.mapper.SysUserRoleMapper;
import cn.qinwh.qbooksystem.service.SysMenuService;
import cn.qinwh.mybatis.qservice.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * 查询用户菜单集合
     *
     * @return 用户所有菜单信息
     */
    @Override
    public List<SysMenu> getUserMenu(Integer userId) {
        List<SysMenu> menuList = sysMenuMapper.selectMenuByUser(userId);
        return menuList;
    }

    @Override
    public List<SysMenu> getUserMenuByRole(Integer roleId) {
        List<SysMenu> menuList = sysMenuMapper.selectMenuByRole(roleId);
        return menuList;
    }
}