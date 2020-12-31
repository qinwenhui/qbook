package cn.qinwh.qbooksystem.mapper;

import cn.qinwh.qbooksystem.entity.SysMenu;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysMenuMapper extends Mapper<SysMenu> {

    /**
     * @Description: 通过角色编号查询该角色菜单
     * @Param: [roleId]
     * @return: java.util.List<cn.qinwh.qbooksystem.entity.SysMenu>
     * @Author: qinwh
     * @Date: 2020/12/30
     */
    List<SysMenu> selectMenuByRole(@Param("roleId") Integer roleId);

    /**
    * @Description: 通过用户编号查询该用户所有角色的菜单权限
    * @Param: [userId]
    * @return: java.util.List<cn.qinwh.qbooksystem.entity.SysMenu>
    * @Author: qinwh
    * @Date: 2020/12/31
    */
    List<SysMenu> selectMenuByUser(@Param("userId") Integer userId);
}