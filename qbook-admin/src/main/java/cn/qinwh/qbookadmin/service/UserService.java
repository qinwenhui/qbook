package cn.qinwh.qbookadmin.service;

import cn.qinwh.mybatis.qservice.common.BaseService;
import cn.qinwh.qbookadmin.bo.UserQuery;
import cn.qinwh.qbookadmin.entity.User;
import com.github.pagehelper.PageInfo;

public interface UserService extends BaseService<User> {

    PageInfo<User> selectByBo(UserQuery bo);
}