package cn.qinwh.qbooksystem.service.impl;

import cn.qinwh.qbooksystem.entity.SysPermission;
import cn.qinwh.qbooksystem.entity.SysRolePermission;
import cn.qinwh.qbooksystem.entity.SysUserRole;
import cn.qinwh.qbooksystem.mapper.SysUserRoleMapper;
import cn.qinwh.qbooksystem.service.SysPermissionService;
import cn.qinwh.mybatis.qservice.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermission> implements SysPermissionService {

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Override
    public List<SysPermission> getUserPermission(Integer userId) {
        List<SysPermission> permissionList = new ArrayList<>();
        //获取该用户的所有角色
        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(userId);
        List<SysUserRole> userRoleList = userRoleMapper.select(userRole);
        for(SysUserRole tmpUserRole : userRoleList){
            SysRolePermission rolePermission = new SysRolePermission();
            rolePermission.setRoleId(tmpUserRole.getRoleId());
            SysPermission permission = mapper.selectByPrimaryKey(rolePermission.getPermissionId());
            permissionList.add(permission);
        }
        return permissionList;
    }
}