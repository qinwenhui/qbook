package cn.qinwh.qbookapp.vo;

import lombok.Data;

/**
 * @program: qbook
 * @description: 书籍详情返回
 * @author: qinwh
 * @create: 2021-01-16 14:18
 **/
@Data
public class BookVo {

    private Integer id;

    //标题
    private String title;

    /**
     * 上架状态:0=下架,1=正常
     */
    private Boolean status;

    /**
     * 浏览量
     */
    private Integer view;

    /**
     * 封面图片
     */
    private String image;

    /**
     * 类型(漫画1小说2)
     */
    private String type;

    /**
     * 作者
     */
    private String auther;

    /**
     * 订阅量
     */
    private Integer mark;

    /**
     * 进度:0=连载,1=完结
     */
    private Boolean mhstatus;

    /**
     * 推荐:0=OFF,1=ON
     */
    private Boolean tjswitch;

    /**
     * 收费状态:0=免费,1=收费
     */
    private Boolean isfree;

    /**
     * 最新章节
     */
    private String lastChapterTitle;

    /**
     * 精品:0=OFF,1=ON
     */
    private Boolean isjingpin;

    /**
     * 限免:0=OFF,1=ON
     */
    private Boolean xianmian;

    /**
     * 横着的封面图
     */
    private String cover;

    /**
     * 热门:0=OFF,1=ON
     */
    private Boolean ishot;

    /**
     * 独家:0=OFF,1=ON
     */
    private Boolean issole;

    /**
     * 新作:0=OFF,1=ON
     */
    private Boolean isnew;

    /**
     * 18+:0=OFF,1=ON
     */
    private Boolean h;

    /**
     * VIP专属:0=OFF,1=ON
     */
    private Boolean vipcanread;

    /**
     * 评分
     */
    private String pingfen;

    /**
     * 描述
     */
    private String desc;
}
