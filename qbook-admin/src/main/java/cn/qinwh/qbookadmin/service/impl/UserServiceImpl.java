package cn.qinwh.qbookadmin.service.impl;

import cn.qinwh.mybatis.qservice.common.BaseServiceImpl;
import cn.qinwh.qbookadmin.bo.UserQuery;
import cn.qinwh.qbookadmin.entity.User;
import cn.qinwh.qbookadmin.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Override
    public PageInfo<User> selectByBo(UserQuery bo) {
        PageHelper.startPage(Integer.parseInt(bo.getPageNo()), Integer.parseInt(bo.getPageSize()));
        List<User> users = mapper.selectAll();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }
}