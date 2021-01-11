package cn.qinwh.qbookadmin.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Table(name = "q_user")
public class User implements Serializable {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 支付密码
     */
    private String paypass;

    /**
     * 注册时间
     */
    @Column(name = "register_time")
    private Date registerTime;

    /**
     * 最后登录时间
     */
    @Column(name = "last_time")
    private Date lastTime;

    /**
     * vip状态:1=是,0=否
     */
    @Column(name = "isvip_status")
    private Integer isvipStatus;

    /**
     * vip到期时间
     */
    private Date viptime;

    /**
     * 头像
     */
    private String headimage;

    /**
     * 状态:1=正常,0=冻结
     */
    private Integer status;

    /**
     * 余额
     */
    private BigDecimal money;

    /**
     * 书币
     */
    private Integer score;

    /**
     * 等级
     */
    private Integer lv;

    /**
     * 上级ID
     */
    private Integer pid;

    /**
     * 路径
     */
    @Column(name = "path_id")
    private String pathId;

    /**
     * 注册ip
     */
    @Column(name = "register_ip")
    private String registerIp;

    /**
     * 登录IP
     */
    @Column(name = "login_ip")
    private String loginIp;

    /**
     * 登录次数
     */
    @Column(name = "login_nums")
    private Integer loginNums;

    /**
     * 用户客户端信息
     */
    private String useragent;

    /**
     * 类别:0=临时,1=注册
     */
    private Integer type;

    /**
     * 连续签到次数
     */
    private Integer lxqd;

    /**
     * 是不是代理商 0 不是 1是的
     */
    @Column(name = "is_agent")
    private Boolean isAgent;

    /**
     * 代理商扣量（0走系统配置-1表示不扣量）
     */
    @Column(name = "has_kou")
    private Integer hasKou;

    /**
     * 扣量统计标识
     */
    @Column(name = "tj_kou")
    private Integer tjKou;

    /**
     * 个人皮肤
     */
    private String myui;

    /**
     * 是否第一次
     */
    @Column(name = "is_first")
    private Integer isFirst;

    /**
     * 来源域名
     */
    private Integer domain;

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
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取手机号
     *
     * @return phone - 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取支付密码
     *
     * @return paypass - 支付密码
     */
    public String getPaypass() {
        return paypass;
    }

    /**
     * 设置支付密码
     *
     * @param paypass 支付密码
     */
    public void setPaypass(String paypass) {
        this.paypass = paypass == null ? null : paypass.trim();
    }

    /**
     * 获取注册时间
     *
     * @return register_time - 注册时间
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * 设置注册时间
     *
     * @param registerTime 注册时间
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 获取最后登录时间
     *
     * @return last_time - 最后登录时间
     */
    public Date getLastTime() {
        return lastTime;
    }

    /**
     * 设置最后登录时间
     *
     * @param lastTime 最后登录时间
     */
    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * 获取vip状态:1=是,0=否
     *
     * @return isvip_status - vip状态:1=是,0=否
     */
    public Integer getIsvipStatus() {
        return isvipStatus;
    }

    /**
     * 设置vip状态:1=是,0=否
     *
     * @param isvipStatus vip状态:1=是,0=否
     */
    public void setIsvipStatus(Integer isvipStatus) {
        this.isvipStatus = isvipStatus;
    }

    /**
     * 获取vip到期时间
     *
     * @return viptime - vip到期时间
     */
    public Date getViptime() {
        return viptime;
    }

    /**
     * 设置vip到期时间
     *
     * @param viptime vip到期时间
     */
    public void setViptime(Date viptime) {
        this.viptime = viptime;
    }

    /**
     * 获取头像
     *
     * @return headimage - 头像
     */
    public String getHeadimage() {
        return headimage;
    }

    /**
     * 设置头像
     *
     * @param headimage 头像
     */
    public void setHeadimage(String headimage) {
        this.headimage = headimage == null ? null : headimage.trim();
    }

    /**
     * 获取状态:1=正常,0=冻结
     *
     * @return status - 状态:1=正常,0=冻结
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态:1=正常,0=冻结
     *
     * @param status 状态:1=正常,0=冻结
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取余额
     *
     * @return money - 余额
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * 设置余额
     *
     * @param money 余额
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 获取书币
     *
     * @return score - 书币
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置书币
     *
     * @param score 书币
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 获取等级
     *
     * @return lv - 等级
     */
    public Integer getLv() {
        return lv;
    }

    /**
     * 设置等级
     *
     * @param lv 等级
     */
    public void setLv(Integer lv) {
        this.lv = lv;
    }

    /**
     * 获取上级ID
     *
     * @return pid - 上级ID
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置上级ID
     *
     * @param pid 上级ID
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取路径
     *
     * @return path_id - 路径
     */
    public String getPathId() {
        return pathId;
    }

    /**
     * 设置路径
     *
     * @param pathId 路径
     */
    public void setPathId(String pathId) {
        this.pathId = pathId == null ? null : pathId.trim();
    }

    /**
     * 获取注册ip
     *
     * @return register_ip - 注册ip
     */
    public String getRegisterIp() {
        return registerIp;
    }

    /**
     * 设置注册ip
     *
     * @param registerIp 注册ip
     */
    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp == null ? null : registerIp.trim();
    }

    /**
     * 获取登录IP
     *
     * @return login_ip - 登录IP
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 设置登录IP
     *
     * @param loginIp 登录IP
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    /**
     * 获取登录次数
     *
     * @return login_nums - 登录次数
     */
    public Integer getLoginNums() {
        return loginNums;
    }

    /**
     * 设置登录次数
     *
     * @param loginNums 登录次数
     */
    public void setLoginNums(Integer loginNums) {
        this.loginNums = loginNums;
    }

    /**
     * 获取用户客户端信息
     *
     * @return useragent - 用户客户端信息
     */
    public String getUseragent() {
        return useragent;
    }

    /**
     * 设置用户客户端信息
     *
     * @param useragent 用户客户端信息
     */
    public void setUseragent(String useragent) {
        this.useragent = useragent == null ? null : useragent.trim();
    }

    /**
     * 获取类别:0=临时,1=注册
     *
     * @return type - 类别:0=临时,1=注册
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类别:0=临时,1=注册
     *
     * @param type 类别:0=临时,1=注册
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取连续签到次数
     *
     * @return lxqd - 连续签到次数
     */
    public Integer getLxqd() {
        return lxqd;
    }

    /**
     * 设置连续签到次数
     *
     * @param lxqd 连续签到次数
     */
    public void setLxqd(Integer lxqd) {
        this.lxqd = lxqd;
    }

    /**
     * 获取是不是代理商 0 不是 1是的
     *
     * @return is_agent - 是不是代理商 0 不是 1是的
     */
    public Boolean getIsAgent() {
        return isAgent;
    }

    /**
     * 设置是不是代理商 0 不是 1是的
     *
     * @param isAgent 是不是代理商 0 不是 1是的
     */
    public void setIsAgent(Boolean isAgent) {
        this.isAgent = isAgent;
    }

    /**
     * 获取代理商扣量（0走系统配置-1表示不扣量）
     *
     * @return has_kou - 代理商扣量（0走系统配置-1表示不扣量）
     */
    public Integer getHasKou() {
        return hasKou;
    }

    /**
     * 设置代理商扣量（0走系统配置-1表示不扣量）
     *
     * @param hasKou 代理商扣量（0走系统配置-1表示不扣量）
     */
    public void setHasKou(Integer hasKou) {
        this.hasKou = hasKou;
    }

    /**
     * 获取扣量统计标识
     *
     * @return tj_kou - 扣量统计标识
     */
    public Integer getTjKou() {
        return tjKou;
    }

    /**
     * 设置扣量统计标识
     *
     * @param tjKou 扣量统计标识
     */
    public void setTjKou(Integer tjKou) {
        this.tjKou = tjKou;
    }

    /**
     * 获取个人皮肤
     *
     * @return myui - 个人皮肤
     */
    public String getMyui() {
        return myui;
    }

    /**
     * 设置个人皮肤
     *
     * @param myui 个人皮肤
     */
    public void setMyui(String myui) {
        this.myui = myui == null ? null : myui.trim();
    }

    /**
     * 获取是否第一次
     *
     * @return is_first - 是否第一次
     */
    public Integer getIsFirst() {
        return isFirst;
    }

    /**
     * 设置是否第一次
     *
     * @param isFirst 是否第一次
     */
    public void setIsFirst(Integer isFirst) {
        this.isFirst = isFirst;
    }

    /**
     * 获取来源域名
     *
     * @return domain - 来源域名
     */
    public Integer getDomain() {
        return domain;
    }

    /**
     * 设置来源域名
     *
     * @param domain 来源域名
     */
    public void setDomain(Integer domain) {
        this.domain = domain;
    }
}