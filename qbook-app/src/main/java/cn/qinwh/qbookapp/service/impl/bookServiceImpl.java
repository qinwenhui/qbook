package cn.qinwh.qbookapp.service.impl;

import cn.qinwh.qbookapp.entity.book;
import cn.qinwh.qbookapp.service.bookService;
import cn.qinwh.mybatis.qservice.common.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class bookServiceImpl extends BaseServiceImpl<book> implements bookService {
}