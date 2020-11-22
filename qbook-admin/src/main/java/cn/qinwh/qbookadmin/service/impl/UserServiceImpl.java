package cn.qinwh.qbookadmin.service.impl;

import cn.qinwh.mybatis.qservice.common.BaseServiceImpl;
import cn.qinwh.qbookadmin.bo.UserBo;
import cn.qinwh.qbookadmin.entity.User;
import cn.qinwh.qbookadmin.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Override
    public PageInfo<User> selectByBo(UserBo bo) {
        PageHelper.startPage(Integer.parseInt(bo.getPageNo()), Integer.parseInt(bo.getPageSize()));
        Example example = new Example(User.class);
        example.excludeProperties("password", "paypass");
        example.createCriteria().andLike("username", "%"+(bo.getUsername()==null?"":bo.getUsername())+"%")
                .andEqualTo("id", bo.getId()).andEqualTo("phone", bo.getPhone()).andEqualTo("type", bo.getType()).andEqualTo("isvipStatus", bo.getVip());
        List<User> users = mapper.selectByExample(example);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }
}