package cn.qinwh.qbooksystem.mapper;

import cn.qinwh.qbooksystem.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysRoleMapper extends Mapper<SysRole> {
    /**
    * @Description: 根绝用户编号获取该用户的所有角色
    * @Param: [userId]
    * @return: java.util.List<cn.qinwh.qbooksystem.entity.SysRole>
    * @Author: qinwh
    * @Date: 2021/1/5
    */
    List<SysRole> selectByUserId(@Param("userId") Integer userId);
}