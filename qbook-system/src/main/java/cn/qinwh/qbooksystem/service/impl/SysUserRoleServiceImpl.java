package cn.qinwh.qbooksystem.service.impl;

import cn.qinwh.qbooksystem.entity.SysUserRole;
import cn.qinwh.qbooksystem.service.SysUserRoleService;
import cn.qinwh.mybatis.qservice.common.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole> implements SysUserRoleService {
}