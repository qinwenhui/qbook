package cn.qinwh.qbookapp.service.impl;

import cn.qinwh.qbookapp.bo.ChapterListBo;
import cn.qinwh.qbookapp.entity.Chapter;
import cn.qinwh.qbookapp.service.ChapterService;
import cn.qinwh.mybatis.qservice.common.BaseServiceImpl;
import cn.qinwh.qbookapp.vo.ChapterVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class ChapterServiceImpl extends BaseServiceImpl<Chapter> implements ChapterService {
    @Override
    public PageInfo<ChapterVo> queryChapterList(ChapterListBo bo) {
        PageHelper.startPage(Integer.parseInt(bo.getPageNo()), Integer.parseInt(bo.getPageSize()));
        Chapter chapter = new Chapter();
        chapter.setBookId(Integer.parseInt(bo.getBookId()));
        List<Chapter> chapterList = mapper.select(chapter);
        PageInfo<ChapterVo> pageInfo = new PageInfo(chapterList);
        return pageInfo;
    }
}
