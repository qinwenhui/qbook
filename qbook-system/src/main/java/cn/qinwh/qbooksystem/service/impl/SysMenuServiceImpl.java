package cn.qinwh.qbooksystem.service.impl;

import cn.qinwh.qbooksystem.entity.SysMenu;
import cn.qinwh.qbooksystem.service.SysMenuService;
import cn.qinwh.mybatis.qservice.common.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu> implements SysMenuService {
}