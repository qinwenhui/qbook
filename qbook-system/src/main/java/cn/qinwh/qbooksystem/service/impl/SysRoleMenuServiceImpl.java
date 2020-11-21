package cn.qinwh.qbooksystem.service.impl;

import cn.qinwh.qbooksystem.entity.SysRoleMenu;
import cn.qinwh.qbooksystem.service.SysRoleMenuService;
import cn.qinwh.mybatis.qservice.common.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenu> implements SysRoleMenuService {
}