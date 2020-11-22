package cn.qinwh.qbooksystem.service;

import cn.qinwh.qbooksystem.entity.SysMenu;
import cn.qinwh.mybatis.qservice.common.BaseService;

import java.util.List;

public interface SysMenuService extends BaseService<SysMenu> {

    List<SysMenu> getUserMenu(Integer userId);
}