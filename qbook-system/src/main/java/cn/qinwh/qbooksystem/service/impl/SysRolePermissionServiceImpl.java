package cn.qinwh.qbooksystem.service.impl;

import cn.qinwh.qbooksystem.entity.SysRolePermission;
import cn.qinwh.qbooksystem.service.SysRolePermissionService;
import cn.qinwh.mybatis.qservice.common.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class SysRolePermissionServiceImpl extends BaseServiceImpl<SysRolePermission> implements SysRolePermissionService {
}