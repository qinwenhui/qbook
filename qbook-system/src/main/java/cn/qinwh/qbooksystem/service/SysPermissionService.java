package cn.qinwh.qbooksystem.service;

import cn.qinwh.qbooksystem.entity.SysPermission;
import cn.qinwh.mybatis.qservice.common.BaseService;

import java.util.List;

public interface SysPermissionService extends BaseService<SysPermission> {

    List<SysPermission> getUserPermission(Integer userId);
}