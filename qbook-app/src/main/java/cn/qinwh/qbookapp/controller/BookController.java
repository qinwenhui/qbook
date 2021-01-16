package cn.qinwh.qbookapp.controller;

import cn.qinwh.qbookapp.entity.Book;
import cn.qinwh.qbookapp.service.BookService;
import cn.qinwh.qbookapp.vo.BookVo;
import cn.qinwh.qbookcommon.utils.PropertiesUtils;
import cn.qinwh.qbookcommon.utils.ReturnMsg;
import cn.qinwh.qbooksystem.annotation.NoLogin;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: qbook
 * @description: 书籍相关
 * @author: qinwh
 * @create: 2021-01-15 14:50
 **/
@Controller
@RequestMapping("/app/book")
@ResponseBody
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/info")
    @NoLogin
    public ReturnMsg info(Integer id){
        Book book = bookService.queryByPrimaryKey(id);
        BookVo bookVo = PropertiesUtils.copy(book, BookVo.class);
        return ReturnMsg.fail("查询成功", bookVo);
    }
}
