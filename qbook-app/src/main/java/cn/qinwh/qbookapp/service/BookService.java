package cn.qinwh.qbookapp.service;

import cn.qinwh.qbookapp.entity.Book;
import cn.qinwh.mybatis.qservice.common.BaseService;
import cn.qinwh.qbookcommon.utils.ReturnMsg;

public interface BookService extends BaseService<Book> {
    /**
    * @Description: 获取书籍信息
    * @Param: [id]
    * @return: cn.qinwh.qbookcommon.utils.ReturnMsg
    * @Author: qinwh
    * @Date: 2021/1/27
    */
    ReturnMsg getBook(Integer id);
}