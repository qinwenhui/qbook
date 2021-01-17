package cn.qinwh.qbookapp.controller;

import cn.qinwh.qbookapp.bo.ChapterListBo;
import cn.qinwh.qbookapp.entity.Book;
import cn.qinwh.qbookapp.service.BookService;
import cn.qinwh.qbookapp.service.ChapterService;
import cn.qinwh.qbookapp.vo.BookVo;
import cn.qinwh.qbookapp.vo.ChapterVo;
import cn.qinwh.qbookcommon.utils.PropertiesUtils;
import cn.qinwh.qbookcommon.utils.ReturnMsg;
import cn.qinwh.qbooksystem.annotation.NoLogin;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

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

    private final ChapterService chapterService;

    @GetMapping("/info")
    public ReturnMsg info(Integer id){
        Book book = bookService.queryByPrimaryKey(id);
        BookVo bookVo = PropertiesUtils.copy(book, BookVo.class);
        return ReturnMsg.success("查询成功", bookVo);
    }

    @GetMapping("/chapterList")
    @NoLogin
    public ReturnMsg chapterList(@Valid ChapterListBo bo){
        //通过书籍编号获取该书籍的章节列表
        PageInfo<ChapterVo> result = chapterService.queryChapterList(bo);
        return ReturnMsg.success("查询成功", result);
    }
}
