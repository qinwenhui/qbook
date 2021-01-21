package cn.qinwh.qbookapp.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "q_swipe")
public class Swipe implements Serializable {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 类型，0=书籍漫画，1=其他、广告
     */
    private Integer type;

    /**
     * 外链，type=1时会用到
     */
    private String url;

    /**
     * 图片地址
     */
    private String image;

    /**
     * 是否可见，0=不可见，1=可见
     */
    private Boolean visible;

    private static final long serialVersionUID = 1L;

    /**
     * @return Id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取类型，0=书籍漫画，1=其他、广告
     *
     * @return type - 类型，0=书籍漫画，1=其他、广告
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型，0=书籍漫画，1=其他、广告
     *
     * @param type 类型，0=书籍漫画，1=其他、广告
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取外链，type=1时会用到
     *
     * @return url - 外链，type=1时会用到
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置外链，type=1时会用到
     *
     * @param url 外链，type=1时会用到
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 获取图片地址
     *
     * @return image - 图片地址
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置图片地址
     *
     * @param image 图片地址
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}