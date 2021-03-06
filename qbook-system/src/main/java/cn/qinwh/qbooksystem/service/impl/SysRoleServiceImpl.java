package cn.qinwh.qbooksystem.service.impl;

import cn.qinwh.qbooksystem.entity.*;
import cn.qinwh.qbooksystem.mapper.*;
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
    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;


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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateRoleAndPermission(Integer roleId, Integer[] permissionIds) {
        try{
            //先把所有的已存在的关联置为无效
            Example example = new Example(SysRolePermission.class);
            example.createCriteria().andEqualTo("roleId", roleId);
            SysRolePermission sysRolePermission = new SysRolePermission();
            sysRolePermission.setStatus(1);
            sysRolePermissionMapper.updateByExampleSelective(sysRolePermission, example);
            //查询已经存在的关联关系
            List<SysRolePermission> rolePermissionList = sysRolePermissionMapper.selectByExample(example);
            for(Integer permissionId: permissionIds){
                //先看看存不存在数据
                SysRolePermission isExist = null;
                for(SysRolePermission tmp: rolePermissionList){
                    if(tmp.getPermissionId().equals(permissionId)){
                        isExist = tmp;
                        break;
                    }
                }
                SysRolePermission rolePermission = new SysRolePermission();
                rolePermission.setPermissionId(permissionId);
                rolePermission.setRoleId(roleId);
                rolePermission.setStatus(0);
                if(isExist != null){
                    //已经存在数据，只需要把状态改了
                    rolePermission.setId(isExist.getId());
                    sysRolePermissionMapper.updateByPrimaryKeySelective(rolePermission);
                }else{
                    //不存在数据，生成
                    sysRolePermissionMapper.insertSelective(rolePermission);
                }
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("修改角色接口权限失败{}", e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<SysRole> getRoleByUser(Integer userId) {
        return sysRoleMapper.selectByUserId(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addRoleByUserId(Integer userId, Integer[] roleIds) {
        try{
            //先把所有的已存在的关联置为无效
            Example example = new Example(SysUserRole.class);
            example.createCriteria().andEqualTo("userId", userId);
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setStatus(1);
            sysUserRoleMapper.updateByExampleSelective(sysUserRole, example);
            //查询已经存在的关联关系
            List<SysUserRole> userRoleList = sysUserRoleMapper.selectByExample(example);
            for(Integer roleId: roleIds){
                //先看看存不存在数据
                SysUserRole isExist = null;
                for(SysUserRole tmp: userRoleList){
                    if(tmp.getRoleId().equals(roleId)){
                        isExist = tmp;
                        break;
                    }
                }
                SysUserRole userRole = new SysUserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                userRole.setStatus(0);
                if(isExist != null){
                    //已经存在数据，只需要把状态改了
                    userRole.setId(isExist.getId());
                    sysUserRoleMapper.updateByPrimaryKeySelective(userRole);
                }else{
                    //不存在数据，生成
                    sysUserRoleMapper.insertSelective(userRole);
                }
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("添加用户角色失败{}", e.getMessage());
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