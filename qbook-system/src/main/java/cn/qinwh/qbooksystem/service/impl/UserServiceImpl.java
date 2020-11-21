package cn.qinwh.qbooksystem.service.impl;

import cn.qinwh.qbooksystem.entity.User;
import cn.qinwh.qbooksystem.service.UserService;
import cn.qinwh.mybatis.qservice.common.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
}