package cn.qinwh.qbookapp.service.impl;

import cn.qinwh.qbookapp.entity.Swipe;
import cn.qinwh.qbookapp.service.SwipeService;
import cn.qinwh.mybatis.qservice.common.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class SwipeServiceImpl extends BaseServiceImpl<Swipe> implements SwipeService {
}