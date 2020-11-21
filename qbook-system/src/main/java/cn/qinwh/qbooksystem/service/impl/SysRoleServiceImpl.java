package cn.qinwh.qbooksystem.service.impl;

import cn.qinwh.qbooksystem.entity.SysRole;
import cn.qinwh.qbooksystem.service.SysRoleService;
import cn.qinwh.mybatis.qservice.common.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {
}