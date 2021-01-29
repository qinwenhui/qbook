package cn.qinwh.qbookapp.service.impl;

import cn.qinwh.qbookapp.entity.Book;
import cn.qinwh.qbookapp.service.BookService;
import cn.qinwh.mybatis.qservice.common.BaseServiceImpl;
import cn.qinwh.qbookapp.vo.BookVo;
import cn.qinwh.qbookcommon.utils.PropertiesUtils;
import cn.qinwh.qbookcommon.utils.ReturnMsg;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class BookServiceImpl extends BaseServiceImpl<Book> implements BookService {
    @Override
    public ReturnMsg getBook(Integer id) {
        if(id == null){
            return ReturnMsg.fail("书籍编号不能为空", null);
        }
        Book book = this.queryByPrimaryKey(id);
        if(book == null){
            return ReturnMsg.fail("暂无该书籍信息", null);
        }
        BookVo bookVo = PropertiesUtils.copy(book, BookVo.class);
        //构造显示的内容

        return ReturnMsg.success("查询成功", bookVo);
    }
}