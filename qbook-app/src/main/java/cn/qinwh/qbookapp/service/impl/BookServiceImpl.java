package cn.qinwh.qbookapp.service.impl;

import cn.qinwh.qbookapp.entity.Book;
import cn.qinwh.qbookapp.service.BookService;
import cn.qinwh.mybatis.qservice.common.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class BookServiceImpl extends BaseServiceImpl<Book> implements BookService {
}