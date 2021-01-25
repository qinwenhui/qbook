package cn.qinwh.qbookapp.vo;

import lombok.Data;

/**
 * @program: qbook
 * @description: 章节返回
 * @author: qinwh
 * @create: 2021-01-16 16:30
 **/
@Data
public class ChapterVo {

    private Integer id;

    private String title;

    private String content;

    private String imagelist;

    private String image;

    private Boolean type;

    private Integer bookId;

    private String isvip;

    private Integer score;
}
