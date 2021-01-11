package cn.qinwh.qbooksystem.service;

import cn.qinwh.qbooksystem.entity.SysRole;
import cn.qinwh.mybatis.qservice.common.BaseService;

import java.util.List;

public interface SysRoleService extends BaseService<SysRole> {
    /**
    * @Description: 修改角色信息和该角色对应菜单权限
    * @Param: [role, menuIds]
    * @return: boolean
    * @Author: qinwh
    * @Date: 2020/12/31
    */
    boolean updateRoleAndMenu(SysRole role, Integer[] menuIds);

    /**
    * @Description: 添加角色和该角色对应的菜单权限
    * @Param: [role, menuIds]
    * @return: boolean
    * @Author: qinwh
    * @Date: 2020/12/31
    */
    boolean addRoleAndMenu(SysRole role, Integer[] menuIds);

    /**
    * @Description: 删除角色以及该角色对应的菜单权限关联
    * @Param: [roleId]
    * @return: boolean
    * @Author: qinwh
    * @Date: 2020/12/31
    */
    boolean deleteRoleAndMenu(Integer roleId);

    /**
    * @Description: 修改该角色的接口权限
    * @Param: [roleId, permissionIds]
    * @return: boolean
    * @Author: qinwh
    * @Date: 2021/1/4
    */
    boolean updateRoleAndPermission(Integer roleId, Integer[] permissionIds);

    /**
    * @Description: 根据用户获取该用户的所有角色
    * @Param: [userId]
    * @return: java.util.List<cn.qinwh.qbooksystem.entity.SysRole>
    * @Author: qinwh
    * @Date: 2021/1/5
    */
    List<SysRole> getRoleByUser(Integer userId);

    /**
    * @Description: 批量添加用户角色
    * @Param: [userId, ids]
    * @return: boolean
    * @Author: qinwh
    * @Date: 2021/1/11
    */
    boolean addRoleByUserId(Integer userId, Integer[] roleIds);
}