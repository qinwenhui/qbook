package cn.qinwh.qbooksystem.service.impl;

import cn.qinwh.qbooksystem.entity.SysUser;
import cn.qinwh.qbooksystem.service.SysUserService;
import cn.qinwh.mybatis.qservice.common.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {
}