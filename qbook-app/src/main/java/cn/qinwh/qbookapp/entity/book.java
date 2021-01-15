package cn.qinwh.qbookapp.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "q_book")
public class book implements Serializable {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 所属栏目:id:name
     */
    @Column(name = "lanmu_id")
    private Integer lanmuId;

    /**
     * 添加时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 排序
     */
    private String sort;

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
     * 作者id
     */
    @Column(name = "author_id")
    private Integer authorId;

    /**
     * 订阅量
     */
    private Integer mark;

    /**
     * 题材:id:name
     */
    @Column(name = "ticai_id")
    private Integer ticaiId;

    /**
     * 读者群:id:name
     */
    @Column(name = "duzhequn_id")
    private Integer duzhequnId;

    /**
     * 地域:id:name
     */
    @Column(name = "diyu_id")
    private Integer diyuId;

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
     * 采集标识
     */
    private String cjid;

    /**
     * 采集状态:0=NO,1=YES
     */
    private Boolean cjstatus;

    /**
     * 采集渠道
     */
    private String cjname;

    /**
     * 标签
     */
    private String keyword;

    /**
     * 最新章节
     */
    @Column(name = "last_chapter_title")
    private String lastChapterTitle;

    /**
     * 被搜索次数
     */
    private Integer searchnums;

    /**
     * 最新章节用于检测
     */
    @Column(name = "last_chapter")
    private String lastChapter;

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

    private static final long serialVersionUID = 1L;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取所属栏目:id:name
     *
     * @return lanmu_id - 所属栏目:id:name
     */
    public Integer getLanmuId() {
        return lanmuId;
    }

    /**
     * 设置所属栏目:id:name
     *
     * @param lanmuId 所属栏目:id:name
     */
    public void setLanmuId(Integer lanmuId) {
        this.lanmuId = lanmuId;
    }

    /**
     * 获取添加时间
     *
     * @return create_time - 添加时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置添加时间
     *
     * @param createTime 添加时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public String getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }

    /**
     * 获取上架状态:0=下架,1=正常
     *
     * @return status - 上架状态:0=下架,1=正常
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置上架状态:0=下架,1=正常
     *
     * @param status 上架状态:0=下架,1=正常
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * 获取浏览量
     *
     * @return view - 浏览量
     */
    public Integer getView() {
        return view;
    }

    /**
     * 设置浏览量
     *
     * @param view 浏览量
     */
    public void setView(Integer view) {
        this.view = view;
    }

    /**
     * 获取封面图片
     *
     * @return image - 封面图片
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置封面图片
     *
     * @param image 封面图片
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    /**
     * 获取类型(漫画1小说2)
     *
     * @return type - 类型(漫画1小说2)
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型(漫画1小说2)
     *
     * @param type 类型(漫画1小说2)
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取作者
     *
     * @return auther - 作者
     */
    public String getAuther() {
        return auther;
    }

    /**
     * 设置作者
     *
     * @param auther 作者
     */
    public void setAuther(String auther) {
        this.auther = auther == null ? null : auther.trim();
    }

    /**
     * 获取作者id
     *
     * @return author_id - 作者id
     */
    public Integer getAuthorId() {
        return authorId;
    }

    /**
     * 设置作者id
     *
     * @param authorId 作者id
     */
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    /**
     * 获取订阅量
     *
     * @return mark - 订阅量
     */
    public Integer getMark() {
        return mark;
    }

    /**
     * 设置订阅量
     *
     * @param mark 订阅量
     */
    public void setMark(Integer mark) {
        this.mark = mark;
    }

    /**
     * 获取题材:id:name
     *
     * @return ticai_id - 题材:id:name
     */
    public Integer getTicaiId() {
        return ticaiId;
    }

    /**
     * 设置题材:id:name
     *
     * @param ticaiId 题材:id:name
     */
    public void setTicaiId(Integer ticaiId) {
        this.ticaiId = ticaiId;
    }

    /**
     * 获取读者群:id:name
     *
     * @return duzhequn_id - 读者群:id:name
     */
    public Integer getDuzhequnId() {
        return duzhequnId;
    }

    /**
     * 设置读者群:id:name
     *
     * @param duzhequnId 读者群:id:name
     */
    public void setDuzhequnId(Integer duzhequnId) {
        this.duzhequnId = duzhequnId;
    }

    /**
     * 获取地域:id:name
     *
     * @return diyu_id - 地域:id:name
     */
    public Integer getDiyuId() {
        return diyuId;
    }

    /**
     * 设置地域:id:name
     *
     * @param diyuId 地域:id:name
     */
    public void setDiyuId(Integer diyuId) {
        this.diyuId = diyuId;
    }

    /**
     * 获取进度:0=连载,1=完结
     *
     * @return mhstatus - 进度:0=连载,1=完结
     */
    public Boolean getMhstatus() {
        return mhstatus;
    }

    /**
     * 设置进度:0=连载,1=完结
     *
     * @param mhstatus 进度:0=连载,1=完结
     */
    public void setMhstatus(Boolean mhstatus) {
        this.mhstatus = mhstatus;
    }

    /**
     * 获取推荐:0=OFF,1=ON
     *
     * @return tjswitch - 推荐:0=OFF,1=ON
     */
    public Boolean getTjswitch() {
        return tjswitch;
    }

    /**
     * 设置推荐:0=OFF,1=ON
     *
     * @param tjswitch 推荐:0=OFF,1=ON
     */
    public void setTjswitch(Boolean tjswitch) {
        this.tjswitch = tjswitch;
    }

    /**
     * 获取收费状态:0=免费,1=收费
     *
     * @return isfree - 收费状态:0=免费,1=收费
     */
    public Boolean getIsfree() {
        return isfree;
    }

    /**
     * 设置收费状态:0=免费,1=收费
     *
     * @param isfree 收费状态:0=免费,1=收费
     */
    public void setIsfree(Boolean isfree) {
        this.isfree = isfree;
    }

    /**
     * 获取采集标识
     *
     * @return cjid - 采集标识
     */
    public String getCjid() {
        return cjid;
    }

    /**
     * 设置采集标识
     *
     * @param cjid 采集标识
     */
    public void setCjid(String cjid) {
        this.cjid = cjid == null ? null : cjid.trim();
    }

    /**
     * 获取采集状态:0=NO,1=YES
     *
     * @return cjstatus - 采集状态:0=NO,1=YES
     */
    public Boolean getCjstatus() {
        return cjstatus;
    }

    /**
     * 设置采集状态:0=NO,1=YES
     *
     * @param cjstatus 采集状态:0=NO,1=YES
     */
    public void setCjstatus(Boolean cjstatus) {
        this.cjstatus = cjstatus;
    }

    /**
     * 获取采集渠道
     *
     * @return cjname - 采集渠道
     */
    public String getCjname() {
        return cjname;
    }

    /**
     * 设置采集渠道
     *
     * @param cjname 采集渠道
     */
    public void setCjname(String cjname) {
        this.cjname = cjname == null ? null : cjname.trim();
    }

    /**
     * 获取标签
     *
     * @return keyword - 标签
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * 设置标签
     *
     * @param keyword 标签
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    /**
     * 获取最新章节
     *
     * @return last_chapter_title - 最新章节
     */
    public String getLastChapterTitle() {
        return lastChapterTitle;
    }

    /**
     * 设置最新章节
     *
     * @param lastChapterTitle 最新章节
     */
    public void setLastChapterTitle(String lastChapterTitle) {
        this.lastChapterTitle = lastChapterTitle == null ? null : lastChapterTitle.trim();
    }

    /**
     * 获取被搜索次数
     *
     * @return searchnums - 被搜索次数
     */
    public Integer getSearchnums() {
        return searchnums;
    }

    /**
     * 设置被搜索次数
     *
     * @param searchnums 被搜索次数
     */
    public void setSearchnums(Integer searchnums) {
        this.searchnums = searchnums;
    }

    /**
     * 获取最新章节用于检测
     *
     * @return last_chapter - 最新章节用于检测
     */
    public String getLastChapter() {
        return lastChapter;
    }

    /**
     * 设置最新章节用于检测
     *
     * @param lastChapter 最新章节用于检测
     */
    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter == null ? null : lastChapter.trim();
    }

    /**
     * 获取精品:0=OFF,1=ON
     *
     * @return isjingpin - 精品:0=OFF,1=ON
     */
    public Boolean getIsjingpin() {
        return isjingpin;
    }

    /**
     * 设置精品:0=OFF,1=ON
     *
     * @param isjingpin 精品:0=OFF,1=ON
     */
    public void setIsjingpin(Boolean isjingpin) {
        this.isjingpin = isjingpin;
    }

    /**
     * 获取限免:0=OFF,1=ON
     *
     * @return xianmian - 限免:0=OFF,1=ON
     */
    public Boolean getXianmian() {
        return xianmian;
    }

    /**
     * 设置限免:0=OFF,1=ON
     *
     * @param xianmian 限免:0=OFF,1=ON
     */
    public void setXianmian(Boolean xianmian) {
        this.xianmian = xianmian;
    }

    /**
     * 获取横着的封面图
     *
     * @return cover - 横着的封面图
     */
    public String getCover() {
        return cover;
    }

    /**
     * 设置横着的封面图
     *
     * @param cover 横着的封面图
     */
    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    /**
     * 获取热门:0=OFF,1=ON
     *
     * @return ishot - 热门:0=OFF,1=ON
     */
    public Boolean getIshot() {
        return ishot;
    }

    /**
     * 设置热门:0=OFF,1=ON
     *
     * @param ishot 热门:0=OFF,1=ON
     */
    public void setIshot(Boolean ishot) {
        this.ishot = ishot;
    }

    /**
     * 获取独家:0=OFF,1=ON
     *
     * @return issole - 独家:0=OFF,1=ON
     */
    public Boolean getIssole() {
        return issole;
    }

    /**
     * 设置独家:0=OFF,1=ON
     *
     * @param issole 独家:0=OFF,1=ON
     */
    public void setIssole(Boolean issole) {
        this.issole = issole;
    }

    /**
     * 获取新作:0=OFF,1=ON
     *
     * @return isnew - 新作:0=OFF,1=ON
     */
    public Boolean getIsnew() {
        return isnew;
    }

    /**
     * 设置新作:0=OFF,1=ON
     *
     * @param isnew 新作:0=OFF,1=ON
     */
    public void setIsnew(Boolean isnew) {
        this.isnew = isnew;
    }

    /**
     * 获取18+:0=OFF,1=ON
     *
     * @return h - 18+:0=OFF,1=ON
     */
    public Boolean getH() {
        return h;
    }

    /**
     * 设置18+:0=OFF,1=ON
     *
     * @param h 18+:0=OFF,1=ON
     */
    public void setH(Boolean h) {
        this.h = h;
    }

    /**
     * 获取VIP专属:0=OFF,1=ON
     *
     * @return vipcanread - VIP专属:0=OFF,1=ON
     */
    public Boolean getVipcanread() {
        return vipcanread;
    }

    /**
     * 设置VIP专属:0=OFF,1=ON
     *
     * @param vipcanread VIP专属:0=OFF,1=ON
     */
    public void setVipcanread(Boolean vipcanread) {
        this.vipcanread = vipcanread;
    }

    /**
     * 获取评分
     *
     * @return pingfen - 评分
     */
    public String getPingfen() {
        return pingfen;
    }

    /**
     * 设置评分
     *
     * @param pingfen 评分
     */
    public void setPingfen(String pingfen) {
        this.pingfen = pingfen == null ? null : pingfen.trim();
    }

    /**
     * 获取描述
     *
     * @return desc - 描述
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置描述
     *
     * @param desc 描述
     */
    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }
}