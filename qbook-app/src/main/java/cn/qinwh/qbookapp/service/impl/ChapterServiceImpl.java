package cn.qinwh.qbookapp.service.impl;

import cn.qinwh.qbookapp.bo.ChapterListBo;
import cn.qinwh.qbookapp.entity.Book;
import cn.qinwh.qbookapp.entity.Chapter;
import cn.qinwh.qbookapp.service.BookService;
import cn.qinwh.qbookapp.service.ChapterService;
import cn.qinwh.mybatis.qservice.common.BaseServiceImpl;
import cn.qinwh.qbookapp.vo.ChapterVo;
import cn.qinwh.qbookcommon.utils.FtpUtils;
import cn.qinwh.qbookcommon.utils.PropertiesUtils;
import cn.qinwh.qbookcommon.utils.ReturnMsg;
import cn.qinwh.qbooksystem.entity.SysUser;
import cn.qinwh.qbooksystem.utils.LoginUserUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class ChapterServiceImpl extends BaseServiceImpl<Chapter> implements ChapterService {

    private final BookService bookService;

    @Override
    public PageInfo<ChapterVo> queryChapterList(ChapterListBo bo) {
        PageHelper.startPage(Integer.parseInt(bo.getPageNo()), Integer.parseInt(bo.getPageSize()));
        Chapter chapter = new Chapter();
        chapter.setBookId(Integer.parseInt(bo.getBookId()));
        List<Chapter> chapterList = mapper.select(chapter);
        PageInfo<ChapterVo> pageInfo = new PageInfo(chapterList);
        return pageInfo;
    }

    @Override
    public ReturnMsg getChapter(Integer id) {
        if(id == null){
            return ReturnMsg.fail("章节编号不能为空", null);
        }
        Chapter chapter = mapper.selectByPrimaryKey(id);
        ChapterVo chapterVo = PropertiesUtils.copy(chapter, ChapterVo.class);
        //读取文件
        String content = FtpUtils.readFile(chapterVo.getContent());
        String littleContent = content.substring(0, 50) + "...";

        //判断该章节是否收费 0=免费 1=VIP
        if("0".equals(chapter.getIsvip())){
            chapterVo.setContent(content);
            return ReturnMsg.success("查询成功", chapterVo);
        }else{
            //该章节收费，先查看这本书是否限免
            Book book = bookService.queryByPrimaryKey(chapter.getBookId());
            if(book.getIsfree()){
                //这本书限免
                chapterVo.setContent(content);
                return ReturnMsg.success("查询成功", chapterVo);
            }
            //该章节收费，检查用户是否登录
            SysUser loginUser = LoginUserUtils.getLoginUser();
            if(loginUser == null){
                //用户未登录，提醒用户登录再查看,只给看前部分一点
                chapterVo.setContent(littleContent);
                return ReturnMsg.success("用户未登录", chapterVo);
            }else{
                //该用户已登录，检查是否已经购买过该章节...略
                if(true){
                    //已经购买
                    chapterVo.setContent(content);
                    return ReturnMsg.success("购买成功", chapterVo);
                }else{
                    //尚未购买，提醒用户是否购买
                    chapterVo.setContent(littleContent);
                    return ReturnMsg.success("尚未购买", chapterVo);
                }
            }
        }
    }
}
