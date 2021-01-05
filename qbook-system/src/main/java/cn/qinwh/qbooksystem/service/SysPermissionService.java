package cn.qinwh.qbooksystem.service;

import cn.qinwh.qbooksystem.entity.SysPermission;
import cn.qinwh.mybatis.qservice.common.BaseService;

import java.util.List;

public interface SysPermissionService extends BaseService<SysPermission> {

    /**
    * @Description: 根据用户编号获取权限
    * @Param: [userId]
    * @return: java.util.List<cn.qinwh.qbooksystem.entity.SysPermission>
    * @Author: qinwh
    * @Date: 2021/1/5
    */
    List<SysPermission> getUserPermission(Integer userId);

    /**
    * @Description: 根据角色编号获取权限
    * @Param: [roleId]
    * @return: java.util.List<cn.qinwh.qbooksystem.entity.SysPermission>
    * @Author: qinwh
    * @Date: 2021/1/5
    */
    List<SysPermission> permissionByRole(Integer roleId);
}