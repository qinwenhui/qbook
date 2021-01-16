package cn.qinwh.qbookapp.service;

import cn.qinwh.qbookapp.bo.ChapterListBo;
import cn.qinwh.qbookapp.entity.Chapter;
import cn.qinwh.mybatis.qservice.common.BaseService;
import cn.qinwh.qbookapp.vo.ChapterVo;
import com.github.pagehelper.PageInfo;

public interface ChapterService extends BaseService<Chapter> {
    /**
     * @Description: 通过书籍编号分页获取章节列表
     * @Param: [bo]
     * @return: com.github.pagehelper.PageInfo<cn.qinwh.qbookapp.vo.ChapterVo>
     * @Author: qinwh
     * @Date: 2021/1/16
     */
    PageInfo<ChapterVo> queryChapterList(ChapterListBo bo);
}