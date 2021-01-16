package cn.qinwh.qbookapp.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "q_chapter")
public class Chapter implements Serializable {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 章节标题
     */
    private String title;

    /**
     * 章节封面图
     */
    private String image;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 书籍id
     */
    @Column(name = "book_id")
    private Integer bookId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 性别:0=免费,1=VIP
     */
    private String isvip;

    /**
     * 售价
     */
    private Integer score;

    /**
     * 阅读量
     */
    private Integer view;

    /**
     * 类型:2=小说,1=漫画
     */
    private Boolean type;

    /**
     * 内容
     */
    private String content;

    /**
     * 图片集
     */
    private String imagelist;

    /**
     * 采集标识
     */
    private String cjid;

    /**
     * 采集渠道
     */
    private String cjname;

    /**
     * 上架状态:0=下架,1=正常
     */
    @Column(name = "'switch'")
    private Boolean sjswitch;

    /**
     * 采集状态
     */
    private Boolean cjstatus;

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
     * 获取章节标题
     *
     * @return title - 章节标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置章节标题
     *
     * @param title 章节标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取章节封面图
     *
     * @return image - 章节封面图
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置章节封面图
     *
     * @param image 章节封面图
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
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
     * 获取书籍id
     *
     * @return book_id - 书籍id
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * 设置书籍id
     *
     * @param bookId 书籍id
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取性别:0=免费,1=VIP
     *
     * @return isvip - 性别:0=免费,1=VIP
     */
    public String getIsvip() {
        return isvip;
    }

    /**
     * 设置性别:0=免费,1=VIP
     *
     * @param isvip 性别:0=免费,1=VIP
     */
    public void setIsvip(String isvip) {
        this.isvip = isvip == null ? null : isvip.trim();
    }

    /**
     * 获取售价
     *
     * @return score - 售价
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置售价
     *
     * @param score 售价
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 获取阅读量
     *
     * @return view - 阅读量
     */
    public Integer getView() {
        return view;
    }

    /**
     * 设置阅读量
     *
     * @param view 阅读量
     */
    public void setView(Integer view) {
        this.view = view;
    }

    /**
     * 获取类型:2=小说,1=漫画
     *
     * @return type - 类型:2=小说,1=漫画
     */
    public Boolean getType() {
        return type;
    }

    /**
     * 设置类型:2=小说,1=漫画
     *
     * @param type 类型:2=小说,1=漫画
     */
    public void setType(Boolean type) {
        this.type = type;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取图片集
     *
     * @return imagelist - 图片集
     */
    public String getImagelist() {
        return imagelist;
    }

    /**
     * 设置图片集
     *
     * @param imagelist 图片集
     */
    public void setImagelist(String imagelist) {
        this.imagelist = imagelist == null ? null : imagelist.trim();
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
     * 获取上架状态:0=下架,1=正常
     *
     * @return sjswitch - 上架状态:0=下架,1=正常
     */
    public Boolean getSjswitch() {
        return sjswitch;
    }

    /**
     * 设置上架状态:0=下架,1=正常
     *
     * @param sjswitch 上架状态:0=下架,1=正常
     */
    public void setSjswitch(Boolean sjswitch) {
        this.sjswitch = sjswitch;
    }

    /**
     * 获取采集状态
     *
     * @return cjstatus - 采集状态
     */
    public Boolean getCjstatus() {
        return cjstatus;
    }

    /**
     * 设置采集状态
     *
     * @param cjstatus 采集状态
     */
    public void setCjstatus(Boolean cjstatus) {
        this.cjstatus = cjstatus;
    }
}