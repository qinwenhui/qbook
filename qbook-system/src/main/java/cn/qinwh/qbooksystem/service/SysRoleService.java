package cn.qinwh.qbooksystem.service;

import cn.qinwh.qbooksystem.entity.SysRole;
import cn.qinwh.mybatis.qservice.common.BaseService;

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
}