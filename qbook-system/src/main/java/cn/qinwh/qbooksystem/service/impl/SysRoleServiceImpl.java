package cn.qinwh.qbooksystem.service.impl;

import cn.qinwh.qbooksystem.entity.SysMenu;
import cn.qinwh.qbooksystem.entity.SysRole;
import cn.qinwh.qbooksystem.entity.SysRoleMenu;
import cn.qinwh.qbooksystem.mapper.SysMenuMapper;
import cn.qinwh.qbooksystem.mapper.SysRoleMapper;
import cn.qinwh.qbooksystem.mapper.SysRoleMenuMapper;
import cn.qinwh.qbooksystem.service.SysRoleService;
import cn.qinwh.mybatis.qservice.common.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Slf4j
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateRoleAndMenu(SysRole role, Integer[] menuIds) {
        try{
            sysRoleMapper.updateByPrimaryKey(role);
            //先把所有的菜单权限清空
            SysRoleMenu updateExample = new SysRoleMenu();
            updateExample.setStatus(1);
            Example example = new Example(SysRoleMenu.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("roleId", role.getId());
            sysRoleMenuMapper.updateByExampleSelective(updateExample, example);
            for(Integer menuId: menuIds){
                updateRoleMenuDeep(role.getId(), menuId);
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("更新角色以及对应菜单权限失败{}", e.getMessage());
            return false;
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addRoleAndMenu(SysRole role, Integer[] menuIds) {
        try{
            sysRoleMapper.insertSelective(role);
            for(Integer menuId: menuIds){
                updateRoleMenuDeep(role.getId(), menuId);
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("添加角色以及对应菜单权限失败{}", e.getMessage());
            return false;
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteRoleAndMenu(Integer roleId) {
        try{
            SysRole role = new SysRole();
            role.setId(roleId);
            this.deleteByPrimaryKey(role);
            //删除该角色对应的菜单权限关联
            Example example = new Example(SysRoleMenu.class);
            example.createCriteria().andEqualTo("roleId", roleId);
            sysRoleMenuMapper.deleteByExample(example);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("删除角色以及对应菜单权限失败{}", e.getMessage());
            return false;
        }
        return true;
    }

    private void updateRoleMenuDeep(Integer roleId, Integer menuId){
        //先查询看看存不存在数据
        Example roleMenuExample = new Example(SysRoleMenu.class);
        roleMenuExample.createCriteria().andEqualTo("roleId", roleId).andEqualTo("menuId", menuId);
        List<SysRoleMenu> roleMenuList = sysRoleMenuMapper.selectByExample(roleMenuExample);
        SysRoleMenu roleMenu = new SysRoleMenu();
        roleMenu.setRoleId(roleId);
        roleMenu.setMenuId(menuId);
        roleMenu.setStatus(0);
        if(roleMenuList != null && !roleMenuList.isEmpty()){
            //已经存在数据，只需要把状态改了（如果本身就是存在的，既0，就不做改变）
            if(roleMenuList.get(0).getStatus() != 0){
                roleMenu.setId(roleMenuList.get(0).getId());
                sysRoleMenuMapper.updateByPrimaryKey(roleMenu);
            }
        }else{
            //不存在数据，生成
            sysRoleMenuMapper.insertSelective(roleMenu);
        }
        //继续变更该权限的父菜单
        SysMenu menu = sysMenuMapper.selectByPrimaryKey(menuId);
        if(menu != null && menu.getPid() != 0){
            updateRoleMenuDeep(roleId, menu.getPid());
        }
    }
}