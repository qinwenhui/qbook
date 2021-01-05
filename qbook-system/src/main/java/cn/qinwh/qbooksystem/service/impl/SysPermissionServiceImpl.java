package cn.qinwh.qbooksystem.service.impl;

import cn.qinwh.qbooksystem.entity.SysPermission;
import cn.qinwh.qbooksystem.entity.SysRolePermission;
import cn.qinwh.qbooksystem.entity.SysUserRole;
import cn.qinwh.qbooksystem.mapper.SysPermissionMapper;
import cn.qinwh.qbooksystem.mapper.SysRolePermissionMapper;
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
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public List<SysPermission> getUserPermission(Integer userId) {
        List<SysPermission> permissionList = sysPermissionMapper.selectUserPemission(userId);
        return permissionList;
    }

    @Override
    public List<SysPermission> permissionByRole(Integer roleId) {
        return sysPermissionMapper.selectRolePermission(roleId);
    }
}