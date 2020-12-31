package cn.qinwh.qbooksystem.mapper;

import cn.qinwh.qbooksystem.entity.SysPermission;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysPermissionMapper extends Mapper<SysPermission> {
    /**
    * @Description: 通过用户编号获取该用户的接口权限列表
    * @Param: [userId]
    * @return: java.util.List<cn.qinwh.qbooksystem.entity.SysPermission>
    * @Author: qinwh
    * @Date: 2020/12/31
    */
    List<SysPermission> selectUserPemission(@Param("userId") Integer userId);
}